package com.example.testapp.data

interface ContentDataSource {
    suspend fun getAudioBook() : Result<Feed>
    suspend fun getMovie(): Result<Feed>
    suspend fun getPodcast(): Result<Feed>
    suspend fun getDetail(id : String) : Result<ContentDetailResponse>
}