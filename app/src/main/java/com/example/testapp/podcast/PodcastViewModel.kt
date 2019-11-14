package com.example.testapp.podcast

import com.example.testapp.BaseViewModel
import com.example.testapp.data.DefaultRepository
import com.example.testapp.data.Result
import com.example.testapp.data.local.ContentEntity
import javax.inject.Inject

class PodcastViewModel @Inject constructor(
    private val repository: DefaultRepository
) : BaseViewModel() {

    override suspend fun makeRequest(): Result<List<ContentEntity>> {
        return repository.getPodcast()
    }
}