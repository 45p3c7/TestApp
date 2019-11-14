package com.example.testapp.di

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.ContentRepository
import com.example.testapp.data.ContentDataSource
import com.example.testapp.data.DefaultRepository
import com.example.testapp.data.local.ContentDao
import com.example.testapp.data.local.ContentRoomDatabase
import com.example.testapp.data.remote.ContentRemoteDataSource
import com.example.testapp.data.remote.TunesService
import com.example.testapp.data.remote.WebService
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [AppModule.AppBinds::class])
class AppModule {

    @Module
    interface AppBinds {

        @Singleton
        @RemoteDataSource
        @Binds
        fun bindRemoteDataSource(remoteRemoteDataSource: ContentRemoteDataSource) : ContentDataSource

//        @Singleton
//        @LocalDataSource
//        @Binds
//        fun bindLocalDataSource(localDataSource : ContentLocalDataSource) : ContentDataSource

        @Binds
        fun bindRepository(repository: ContentRepository) : DefaultRepository
    }



    @Provides
    fun provideIODispatcher() = Dispatchers.IO

    @Qualifier
    annotation class RemoteDataSource

    @Qualifier
    annotation class LocalDataSource


    @Provides
    fun provideDao(database: ContentRoomDatabase) : ContentDao {
        return database.contentDao()
    }


    @Singleton
    @Provides
    fun provideDb(context : Context) : ContentRoomDatabase {
        return Room.databaseBuilder(
                context,
                ContentRoomDatabase::class.java,
               "content_database"
        ).build()
    }




    @Singleton
    @Provides
    fun provideOkHttp() : OkHttpClient {
       return  OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }



    @Singleton
    @Provides
    fun provideWebService(client: OkHttpClient) : WebService {

        return Retrofit.Builder()
            .baseUrl("https://rss.itunes.apple.com/")
                .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideTunesService() : TunesService {

        return Retrofit.Builder()
            .baseUrl("http://itunes.apple.com/")
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TunesService::class.java)
    }
}