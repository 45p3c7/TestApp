package com.example.testapp.data

import com.example.testapp.data.local.ContentEntity

interface DefaultRepository {
    suspend fun getMovie() : Result<List<ContentEntity>>
    suspend fun getPodcast() : Result<List<ContentEntity>>
    suspend fun getAudibook() : Result<List<ContentEntity>>
    suspend fun getDetail(id : String) : Result<ContentDetailResponse>
    suspend fun handleFavorite(id : String, isFavorite : Boolean)
    suspend fun getFavorites() : Result<List<ContentEntity>>
}