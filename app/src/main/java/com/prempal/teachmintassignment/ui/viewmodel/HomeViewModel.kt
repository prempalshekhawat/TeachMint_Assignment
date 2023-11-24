package com.prempal.teachmintassignment.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prempal.teachmintassignment.data.Movie
import com.prempal.teachmintassignment.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _items = MutableLiveData<List<Movie>>(emptyList())
    val items: LiveData<List<Movie>> = _items

    init {
        viewModelScope.launch {
            _items.value = repository.getItems()
        }
    }

//    private val _items = mutableStateOf<List<Movie>>(emptyList())
//    val items: State<List<Movie>> = _items
//
//    init {
//        viewModelScope.launch {
//            _items.value = repository.getItems()
//        }
//    }

}