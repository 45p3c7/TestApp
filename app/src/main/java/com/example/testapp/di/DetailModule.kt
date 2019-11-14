package com.example.testapp.di

import androidx.lifecycle.ViewModel
import com.example.testapp.detail.ContentDetailFragment
import com.example.testapp.detail.ContentDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class DetailModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun bindDetailFragment() : ContentDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(value = ContentDetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: ContentDetailViewModel) : ViewModel
}