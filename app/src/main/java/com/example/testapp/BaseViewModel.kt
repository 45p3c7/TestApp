package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.Result
import com.example.testapp.data.local.ContentEntity
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    protected val _content = MutableLiveData<List<ContentEntity>>()
    val content: LiveData<List<ContentEntity>>
        get() = _content

    private val _errorMessage = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>>
        get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
    get() = _isLoading



     fun loadContent() {
        viewModelScope.launch {
            if (_content.value == null) {
                _isLoading.value = true
                val result = makeRequest()
                _isLoading.value = false
                when (result) {
                    is Result.Success -> _content.value = result.data
                    is Result.Error -> _errorMessage.value = Event(result.exception.message ?: "")
                }
            }
        }
    }

    protected abstract suspend fun makeRequest() : Result<List<ContentEntity>>
}