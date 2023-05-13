package ru.vorzee.wildberriestest.model

sealed interface ListState {
    object Loading : ListState
    object Error : ListState
    object Idle : ListState
}
