package com.example.testapp.movie

import com.example.testapp.BaseViewModel
import com.example.testapp.data.Content
import com.example.testapp.data.DefaultRepository
import com.example.testapp.data.Result
import com.example.testapp.data.local.ContentEntity
import javax.inject.Inject

class MovieViewModel @Inject constructor(
        private var repository: DefaultRepository
) : BaseViewModel() {

    override suspend fun makeRequest(): Result<List<ContentEntity>> {
        return repository.getMovie()
    }
}