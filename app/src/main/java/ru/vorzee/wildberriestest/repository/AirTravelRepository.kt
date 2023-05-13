package ru.vorzee.wildberriestest.repository

import kotlinx.coroutines.flow.Flow
import ru.vorzee.wildberriestest.dto.Flight

interface AirTravelRepository {

    val data: Flow<List<Flight>>

    suspend fun getAll()

    fun like(flight: Flight)

}