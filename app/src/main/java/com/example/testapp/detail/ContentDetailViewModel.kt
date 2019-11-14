package com.example.testapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.ContentDetail
import com.example.testapp.data.DefaultRepository
import com.example.testapp.data.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentDetailViewModel @Inject constructor(
    private val repository: DefaultRepository
) : ViewModel() {

    private val _detailInfo = MutableLiveData<ContentDetail>()
    val detailInfo : LiveData<ContentDetail>
    get() = _detailInfo


    fun getDetailInformation(id : String) {
        viewModelScope.launch {
            val result = repository.getDetail(id)
            when(result) {
                is Result.Success -> _detailInfo.value = result.data.results?.get(0)
            }
        }
    }

}