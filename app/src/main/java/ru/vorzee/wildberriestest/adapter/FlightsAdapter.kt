package ru.vorzee.wildberriestest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.vorzee.wildberriestest.databinding.CardAirTravelBinding
import ru.vorzee.wildberriestest.dto.Flight


class FlightsAdapter(
    private val onDetails: (flight:Flight) -> Unit
) : ListAdapter<Flight, FlightViewHolder>(
    FlightDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = CardAirTravelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FlightViewHolder(
            binding = binding,
            onDetails = onDetails
        )
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}