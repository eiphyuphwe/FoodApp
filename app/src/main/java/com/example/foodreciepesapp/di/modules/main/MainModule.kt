package com.example.foodreciepesapp.di.modules.main

import com.example.foodreciepesapp.network.main.MainApi
import com.example.foodreciepesapp.ui.main.posts.PostsAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    companion object{
        @Provides
        fun providesAdapter():PostsAdapter{
            return PostsAdapter()
        }

        @Provides
        fun providesMainApi(retrofit: Retrofit) : MainApi{
            return retrofit.create(MainApi::class.java)
        }
    }
}