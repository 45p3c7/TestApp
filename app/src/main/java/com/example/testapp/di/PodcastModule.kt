package com.example.testapp.di

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.testapp.podcast.PodcastFragment
import com.example.testapp.podcast.PodcastViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class PodcastModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun bindPodcastFragment() : PodcastFragment

    @Binds
    @IntoMap
    @ViewModelKey(value = PodcastViewModel::class)
    abstract fun bindPodcastViewModel(viewModel : PodcastViewModel) : ViewModel

}