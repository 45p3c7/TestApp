package com.example.testapp.di

import com.example.testapp.BaseFragment
import com.example.testapp.BaseViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BaseModule {
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    abstract fun baseFragment() : BaseFragment
}