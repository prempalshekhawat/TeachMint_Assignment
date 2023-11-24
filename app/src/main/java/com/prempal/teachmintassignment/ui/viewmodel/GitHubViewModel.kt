package com.prempal.teachmintassignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prempal.teachmintassignment.data.GitSearchResponse
import com.prempal.teachmintassignment.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubViewModel @Inject constructor(private val repository: GitHubRepository) :
    ViewModel() {

//    private val _repos = mutableStateOf<List<GitRepositories>>(emptyList())
//    val repos: State<List<GitRepositories>> = _repos

    private val _repos = MutableLiveData<GitSearchResponse>()
    val repos: LiveData<GitSearchResponse> = _repos

    fun searchRepos(query: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            val result = repository.searchRepos(query, page, perPage)
            _repos.value = result
        }
    }

}