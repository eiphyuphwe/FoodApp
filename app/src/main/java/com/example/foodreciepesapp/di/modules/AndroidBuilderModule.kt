package com.example.foodreciepesapp.di.modules

import com.example.foodreciepesapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

}