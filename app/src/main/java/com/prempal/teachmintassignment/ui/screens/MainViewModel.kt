package com.prempal.teachmintassignment.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.prempal.teachmintassignment.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

//    var query by mutableStateOf("")
//    var data by mutableStateOf("")
    fun items(searchQuery: String) = repository.fetchRemoteRepositories(searchQuery).cachedIn(viewModelScope)

    fun debounceSearch() {
        //Todo: debounce search api call
    }

    fun updateQuery(query: String) {
        items(query)
    }

}