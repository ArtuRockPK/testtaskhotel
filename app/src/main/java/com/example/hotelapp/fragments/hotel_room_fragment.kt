package com.example.hotelapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.hotelapp.R
import com.example.hotelapp.Retrofit.secondScreenData
import com.example.hotelapp.modules.addChip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayoutMediator


interface Callbacks {
    fun roomSelected()
}


class hotel_room_fragment(var apiData:secondScreenData):Fragment() {
    private lateinit var viewPagerTest: ViewPager2
    private lateinit var roomsRecycler: RecyclerView
    private lateinit var data:secondScreenData
    private lateinit var hotelRoomFragmentContext: Context
    private var callbacks: Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = apiData
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        hotelRoomFragmentContext = context
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.rooms_fragment,container,false)
        roomsRecycler = view.findViewById(R.id.recRoomsList) as RecyclerView
        roomsRecycler.layoutManager = LinearLayoutManager(context)
        roomsRecycler.adapter = RoomsFragmentAdapter()
        return view
    }

    private inner class RoomsFragmentHolder(view:View):RecyclerView.ViewHolder(view) {
        val title:TextView = itemView.findViewById(R.id.rec_title_text)
        val price:TextView = itemView.findViewById(R.id.price_text)
        val priceDetails:TextView = itemView.findViewById(R.id.detais_price)
        val bookingButton:Button = itemView.findViewById(R.id.bookingButton)
        val chipGroup:ChipGroup = itemView.findViewById(R.id.recycler_chip_group)
        fun bind(position:Int) {
            title.text = data.rooms[position].name
            val formatted_price = "${data.rooms[position].price / 1000} ${data.rooms[position].price % 1000} ₽"
            price.text = formatted_price
            priceDetails.text = data.rooms[position].price_per

            bookingButton.setOnClickListener {
                callbacks?.roomSelected()
            }
            viewPagerTest = itemView.findViewById(R.id.rec_elem_pager)
            viewPagerTest.adapter = PhotoAdapter(listLinks = data.rooms[position].image_urls)
            TabLayoutMediator(itemView.findViewById(R.id.rec_elem_tab_indicator), viewPagerTest)
            { tab, position ->}.attach()
            addChip(hotelRoomFragmentContext,data.rooms[position].peculiarities,chipGroup)
        }
    }


    private inner class RoomsFragmentAdapter(): RecyclerView.Adapter<RoomsFragmentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsFragmentHolder {
            val view = layoutInflater.inflate(R.layout.room_recycler_element,parent,false)
            return RoomsFragmentHolder(view)
        }

        override fun onBindViewHolder(holder: RoomsFragmentHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return data.rooms.size
        }
    }


}