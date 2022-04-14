package com.example.foodreciepesapp.di.modules.auth

import com.example.foodreciepesapp.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class AuthModule {


    companion object{
        @Provides
        fun providesAuthApi(retrofit: Retrofit) : AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }
}