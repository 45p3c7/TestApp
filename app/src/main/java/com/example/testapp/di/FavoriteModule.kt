package com.example.testapp.di

import androidx.lifecycle.ViewModel
import com.example.testapp.favorite.FavoriteFragment
import com.example.testapp.favorite.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FavoriteModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun bindFavoriteFragment() : FavoriteFragment

    @Binds
    @IntoMap
    @ViewModelKey(value = FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel : FavoriteViewModel) : ViewModel
}