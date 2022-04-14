package com.example.foodreciepesapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.foodreciepesapp.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelProvideFactory(viewModelProviderFactory : ViewModelProviderFactory)
    : ViewModelProvider.Factory
}