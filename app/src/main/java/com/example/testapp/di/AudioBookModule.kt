package com.example.testapp.di

import androidx.lifecycle.ViewModel
import com.example.testapp.audiobook.AudioBookFragment
import com.example.testapp.audiobook.AudioBookViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AudioBookModule {

    @ContributesAndroidInjector( modules = [ViewModelBuilder::class])
    abstract fun audioBookFragment() : AudioBookFragment

    @Binds
    @IntoMap
    @ViewModelKey(AudioBookViewModel::class)
    abstract fun bindViewModel(viewModel: AudioBookViewModel) : ViewModel
}