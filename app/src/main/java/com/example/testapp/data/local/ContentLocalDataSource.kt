package com.example.testapp.data.local

import com.example.testapp.data.*
import javax.inject.Inject

class ContentLocalDataSource @Inject constructor(
        private val contentDao: ContentDao
) {

    suspend fun getAudioBook(): Result<List<ContentEntity>> {
        return Result.Success(contentDao.getContents(BOOK))
    }

    suspend fun getMovie(): Result<List<ContentEntity>> {
        return Result.Success(contentDao.getContents(MOVIE))

    }

    suspend fun getPodcast(): Result<List<ContentEntity>> {
        return Result.Success(contentDao.getContents(PODCAST))

    }

    suspend fun insertContent(contents: List<ContentEntity>): List<ContentEntity> {
        return contentDao.insertAll(contents)
    }

    suspend fun handleFavorite(id : String, isFavorite : Boolean) {
        contentDao.updateFavorite(id, isFavorite)
    }

    suspend fun getFavorites() : Result<List<ContentEntity>> {
        return Result.Success(contentDao.getAllFavorite())
    }
}