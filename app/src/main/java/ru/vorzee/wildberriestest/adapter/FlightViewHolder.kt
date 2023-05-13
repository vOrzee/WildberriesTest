package ru.vorzee.wildberriestest.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.vorzee.wildberriestest.R
import ru.vorzee.wildberriestest.databinding.CardAirTravelBinding
import ru.vorzee.wildberriestest.dto.Flight
import ru.vorzee.wildberriestest.utils.TransformationData.convertDatePublished

class FlightViewHolder(
    private val binding: CardAirTravelBinding,
    private val onDetails: (flight:Flight) -> Unit
)  : RecyclerView.ViewHolder(binding.root) {

    fun bind(flight: Flight) {
        with(binding) {
            val departureStr = "${flight.startCity}\n${convertDatePublished(flight.startDate)}"
            val arrivalStr = "${flight.endCity}\n${convertDatePublished(flight.endDate)}"
            val priceStr = "${flight.price}\u20BD"

            departure.text = departureStr
            arrival.text = arrivalStr
            price.text = priceStr

            favorite.setImageResource(
                if (flight.likedByMe) R.drawable.ic_favorite_aply_48
                else R.drawable.ic_favorite_48
            )

            details.setOnClickListener {
                onDetails(flight)
            }

            root.setOnLongClickListener {
                onDetails(flight)
                true
            }
        }
    }
}