package com.example.testapp.di

import android.content.Context
import com.example.testapp.BaseFragment
import com.example.testapp.TestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AudioBookModule::class,
        MovieModel::class,
        BaseModule::class,
        PodcastModule::class,
        DetailModule::class,
        FavoriteModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<TestApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}