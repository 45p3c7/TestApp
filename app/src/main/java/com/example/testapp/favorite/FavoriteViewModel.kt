package com.example.testapp.favorite

import androidx.lifecycle.viewModelScope
import com.example.testapp.BaseViewModel
import com.example.testapp.data.DefaultRepository
import com.example.testapp.data.Result
import com.example.testapp.data.local.ContentEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
        private val repository: DefaultRepository
) : BaseViewModel() {


    override suspend fun makeRequest(): Result<List<ContentEntity>> {
        return repository.getFavorites()
    }

    fun deleteFavorite(id: String, favorite: Boolean, adapterPosition: Int) {
        viewModelScope.launch {
            val list = _content.value?.toMutableList()
            list?.removeAt(adapterPosition)
            _content.value = list
            repository.handleFavorite(id, favorite)
        }
    }
}