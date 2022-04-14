package com.example.foodreciepesapp.di.modules.main

import com.example.foodreciepesapp.ui.main.posts.PostsFragment
import com.example.foodreciepesapp.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment() : ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment():PostsFragment

}