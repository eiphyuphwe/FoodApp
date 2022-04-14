package com.example.foodreciepesapp.di.modules.auth

import androidx.lifecycle.ViewModel
import com.example.foodreciepesapp.di.modules.ViewModelKey
import com.example.foodreciepesapp.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel):ViewModel
}