package com.example.testapp.data

import com.example.testapp.data.local.ContentEntity
import com.example.testapp.data.local.ContentLocalDataSource
import com.example.testapp.di.AppModule.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContentRepository @Inject constructor(
    @RemoteDataSource private val remoteContentDataSource: ContentDataSource,
    private val localDataSource: ContentLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DefaultRepository {

    override suspend fun getMovie(): Result<List<ContentEntity>> = withContext(ioDispatcher) {
        fetchRemoteOrLocal(
            remoteCall = { remoteContentDataSource.getMovie() },
            localCall = { localDataSource.getMovie() }
        )
    }

    override suspend fun getPodcast(): Result<List<ContentEntity>> = withContext(ioDispatcher) {
        fetchRemoteOrLocal(
            remoteCall = { remoteContentDataSource.getPodcast() },
            localCall = { localDataSource.getPodcast() }
        )
    }

    override suspend fun getAudibook(): Result<List<ContentEntity>> = withContext(ioDispatcher) {
        fetchRemoteOrLocal(
            remoteCall = { remoteContentDataSource.getAudioBook() },
            localCall = { localDataSource.getAudioBook() }
        )
    }

    override suspend fun getDetail(id: String): Result<ContentDetailResponse> {
        return withContext(ioDispatcher) {
            remoteContentDataSource.getDetail(id)
        }
    }

    private suspend fun fetchRemoteOrLocal(
        remoteCall: suspend () -> Result<Feed>,
        localCall: suspend () -> Result<List<ContentEntity>>
    ): Result<List<ContentEntity>> {
        val result = remoteCall()
        when (result) {
            is Result.Error -> result.exception.message
            is Result.Success -> {
                val contentEntity = convertToContentEntity(result.data.feed.results ?: emptyList())
                return Result.Success(localDataSource.insertContent(contentEntity))
            }
        }
        val localResult = localCall()
        if (localResult is Result.Success) return localResult
        return Result.Error(Exception("Fetching error"))
    }

    private fun convertToContentEntity(contents: List<Content>): List<ContentEntity> {
        return contents.map {
            ContentEntity(
                contentId = it.id,
                artistName = it.artistName,
                artistId = it.artistId,
                releaseData = it.releaseData,
                name = it.name,
                artistUrl = it.artistUrl,
                artworkUrl100 = it.artworkUrl100,
                url = it.url,
                kind = it.kind
            )
        }
    }

    override suspend fun handleFavorite(id: String, isFavorite: Boolean) {
        localDataSource.handleFavorite(id, isFavorite)
    }

    override suspend fun getFavorites(): Result<List<ContentEntity>> = withContext(ioDispatcher) {
        val result = localDataSource.getFavorites()
        when(result) {
            is Result.Error -> result
            is Result.Success -> result
        }
    }
}