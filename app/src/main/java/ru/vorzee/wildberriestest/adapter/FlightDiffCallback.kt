package ru.vorzee.wildberriestest.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.vorzee.wildberriestest.dto.Flight

class FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {

    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean = oldItem.searchToken == newItem.searchToken

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean = oldItem == newItem
}