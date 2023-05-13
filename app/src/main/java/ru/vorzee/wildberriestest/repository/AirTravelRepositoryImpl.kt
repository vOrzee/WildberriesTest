package ru.vorzee.wildberriestest.repository

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ru.vorzee.wildberriestest.api.ApiWbTest
import ru.vorzee.wildberriestest.dto.Flight
import ru.vorzee.wildberriestest.dto.FlightResponse
import java.net.SocketTimeoutException

class AirTravelRepositoryImpl : AirTravelRepository {

    private var _data: List<Flight> = listOf()

    private val gson = Gson()

    override val data: Flow<List<Flight>>
        get() = flow {
            while (true) {
                emit(_data)
                delay(500)
            }
        }

    override suspend fun getAll() {
        withContext(Dispatchers.IO) {
            try {
                val responseJson = ApiWbTest.getListData()
                val responseObject: FlightResponse = gson.fromJson(responseJson, FlightResponse::class.java)
                _data = responseObject.flights
            } catch (e: SocketTimeoutException) {
                throw Exception()
            }
        }
    }

    override fun like(flight: Flight) {
        _data = _data.map {
            if (it.searchToken == flight.searchToken) flight.copy(likedByMe = !flight.likedByMe)
            else it
        }
    }
}