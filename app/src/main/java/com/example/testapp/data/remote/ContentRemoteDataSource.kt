package com.example.testapp.data.remote

import com.example.testapp.data.*
import retrofit2.Response
import javax.inject.Inject

class ContentRemoteDataSource @Inject constructor(
    private val webService : WebService,
    private val itunesService: TunesService

    ) : ContentDataSource {

    override suspend fun getAudioBook(): Result<Feed> {
       return try {
            val result = webService.getAudiobook()
            if (result.isSuccessful) {

            }
            return Result.Error(Exception(result.message()))
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getMovie(): Result<Feed> {
        return try {
            val result = webService.getMovie()
            handleResponse(result)
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPodcast(): Result<Feed> {
        return try {
            val result = webService.getPodcast()
            handleResponse(result)
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getDetail(id : String): Result<ContentDetailResponse> {
        return try {
            val result = itunesService.getDetail(id)
            handleResponse(result)
        } catch (e : Exception) {
            Result.Error(e)
        }
    }

    private fun <T> handleResponse(response : Response<T>) : Result<T> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.Success(body)
            }
        }
        return  Result.Error(Exception(response.message()))
    }
}