package ru.vorzee.wildberriestest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import ru.vorzee.wildberriestest.R
import ru.vorzee.wildberriestest.databinding.AirTravelDetailsBinding
import ru.vorzee.wildberriestest.utils.TransformationData.convertDatePublished
import ru.vorzee.wildberriestest.viewmodel.AirTravelViewModel

class AirTravelDetails : Fragment() {

    private lateinit var binding: AirTravelDetailsBinding

    private val viewModel: AirTravelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AirTravelDetailsBinding.inflate(layoutInflater)
        val searchToken = arguments?.getString("searchTokenArg", "")
        lifecycleScope.launchWhenCreated {
            viewModel.data.collectLatest { list ->
                list.find { it.searchToken == searchToken }?.let { flight ->
                    with(binding) {
                        val departureAirportText =
                            "${flight.startLocationCode} - ${flight.startCity}"
                        val arrivalAirportText = "${flight.endLocationCode} - ${flight.endCity}"
                        val priceStr = "${flight.price}\u20BD"

                        departureAirport.text = departureAirportText
                        departureDate.text = convertDatePublished(flight.startDate)

                        arrivalAirport.text = arrivalAirportText
                        returnDate.text = convertDatePublished(flight.endDate)

                        priceFlight.text = priceStr

                        favoriteFlights.setImageResource(
                            if (flight.likedByMe) R.drawable.ic_favorite_aply_48
                            else R.drawable.ic_favorite_48
                        )

                        favoriteFlights.setOnClickListener {
                            viewModel.like(flight)
                        }
                    }
                }
            }
        }
        return binding.root
    }
}