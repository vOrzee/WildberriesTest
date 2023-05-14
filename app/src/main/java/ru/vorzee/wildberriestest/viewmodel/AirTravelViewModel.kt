package ru.vorzee.wildberriestest.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.vorzee.wildberriestest.dto.Flight
import ru.vorzee.wildberriestest.model.ListState
import ru.vorzee.wildberriestest.repository.AirTravelRepository
import ru.vorzee.wildberriestest.repository.AirTravelRepositoryImpl

class AirTravelViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AirTravelRepository = AirTravelRepositoryImpl()

    private var _data: List<Flight> = listOf()

    private var _dataState: ListState = ListState.Idle

    init {
        loadFlights()
    }

    val data: Flow<List<Flight>>
        get() = flow {
            while (true) {
                getData()
                emit(_data)
                delay(500)
            }
        }

    val dataState: Flow<ListState>
        get() = flow {
            while (true) {
                emit(_dataState)
                delay(500)
            }
        }

    fun loadFlights() = viewModelScope.launch {
        try {
            _dataState = ListState.Loading
            repository.getAll()
            _dataState = ListState.Idle
        } catch (e: Exception) {
            _dataState = ListState.Error
        }
    }

    private fun getData() = viewModelScope.launch {
        repository.data.collectLatest {
            _data = it
        }
    }

    fun like(flight: Flight) {
        repository.like(flight)
    }
}