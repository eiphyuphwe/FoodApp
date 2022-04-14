package com.example.foodreciepesapp.di.modules.main

import androidx.lifecycle.ViewModel
import com.example.foodreciepesapp.di.modules.ViewModelKey
import com.example.foodreciepesapp.ui.auth.AuthViewModel
import com.example.foodreciepesapp.ui.main.posts.PostsViewModel
import com.example.foodreciepesapp.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel):ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel) : ViewModel

}