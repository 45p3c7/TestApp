package com.example.testapp.audiobook

import androidx.lifecycle.viewModelScope
import com.example.testapp.BaseViewModel
import com.example.testapp.data.DefaultRepository
import com.example.testapp.data.Result
import com.example.testapp.data.local.ContentEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class AudioBookViewModel @Inject constructor(
    private val repository: DefaultRepository
) : BaseViewModel() {

    override suspend fun makeRequest(): Result<List<ContentEntity>> {
       return repository.getAudibook()
    }

    fun handleFavorite(id: String, isFavorite: Boolean, adapterPosition: Int) {
        viewModelScope.launch {
            val list = content.value
            list?.get(adapterPosition)?.isFavorite = isFavorite
            _content.value = list
            repository.handleFavorite(id, isFavorite)
        }
    }
}