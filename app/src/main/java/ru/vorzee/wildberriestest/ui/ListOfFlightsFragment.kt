package ru.vorzee.wildberriestest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import ru.vorzee.wildberriestest.R
import ru.vorzee.wildberriestest.adapter.FlightsAdapter
import ru.vorzee.wildberriestest.databinding.ListOfFlightsFragmentBinding
import ru.vorzee.wildberriestest.model.ListState
import ru.vorzee.wildberriestest.viewmodel.AirTravelViewModel

class ListOfFlightsFragment : Fragment() {
    private lateinit var binding: ListOfFlightsFragmentBinding
    private lateinit var adapter: FlightsAdapter
    private val viewModel: AirTravelViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ListOfFlightsFragmentBinding.inflate(layoutInflater)
        adapter = FlightsAdapter {
            findNavController().navigate(
                R.id.action_listOfFlightsFragment_to_airTravelDetails3,
                bundleOf("searchTokenArg" to it.searchToken)
            )
        }
        binding.list.adapter = adapter

        lifecycleScope.launchWhenCreated {
            viewModel.dataState.collectLatest { state ->
                when (state) {
                    is ListState.Loading -> {
                        binding.list.isVisible = false
                        binding.progressBar.isVisible = true
                        binding.errorMessage.isVisible = false
                    }

                    is ListState.Idle -> {
                        binding.list.isVisible = true
                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = false
                    }

                    is ListState.Error -> {
                        binding.list.isVisible = false
                        binding.progressBar.isVisible = false
                        binding.errorMessage.isVisible = true
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.data.collectLatest {
                adapter.submitList(it)
            }
        }



        return binding.root
    }
}