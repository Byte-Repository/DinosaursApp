package com.example.dinosaursapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dinosaursapp.network.DinosaursApi
import com.example.dinosaursapp.network.DinosaursPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface DinosaursUiState {
    data class Success(val photos: List<DinosaursPhoto>) : DinosaursUiState
    object Error : DinosaursUiState
    object Loading : DinosaursUiState
}

class DinosaursViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var dinosaursUiState: DinosaursUiState by mutableStateOf(DinosaursUiState.Loading)
        private set

    /**
     * Call getDinosaursPhotos() on init so we can display status immediately.
     */
    init {
        getDinosaursPhotos()
    }

    /**
     * Gets Dinosaurs photos information from the Dinosaurs API Retrofit service and updates
     * [DinosaursPhoto] [List] [MutableList].
     */
    fun getDinosaursPhotos() {
        viewModelScope.launch {
            dinosaursUiState = try {
                DinosaursUiState.Success(DinosaursApi.retrofitService.getPhotos())
            } catch (e: IOException) {
                DinosaursUiState.Error
            }
        }
    }
}



