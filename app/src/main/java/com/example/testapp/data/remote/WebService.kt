package com.example.testapp.data.remote

import com.example.testapp.data.ContentDetailResponse
import com.example.testapp.data.Feed
import com.example.testapp.data.RSSResponse
import com.example.testapp.data.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("api/v1/us/audiobooks/top-audiobooks/all/25/non-explicit.json")
    suspend fun getAudiobook() : Response<Feed>

    @GET("api/v1/us/movies/top-movies/all/25/non-explicit.json")
    suspend fun getMovie() : Response<Feed>

    @GET("api/v1/us/podcasts/top-podcasts/all/25/non-explicit.json")
    suspend fun getPodcast() : Response<Feed>


}

interface TunesService {
    @GET("lookup")
    suspend fun getDetail(@Query("id") id : String) : Response<ContentDetailResponse>
}