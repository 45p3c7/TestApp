package com.example.testapp.di

import androidx.lifecycle.ViewModel
import com.example.testapp.movie.MovieFragment
import com.example.testapp.movie.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MovieModel {

    @ContributesAndroidInjector( modules = [ViewModelBuilder::class])
    abstract fun movieFragment() : MovieFragment

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindViewModel(viewModel: MovieViewModel) : ViewModel
}