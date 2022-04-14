package com.example.foodreciepesapp.di.modules

import com.example.foodreciepesapp.di.modules.auth.AuthModule
import com.example.foodreciepesapp.di.modules.auth.AuthViewModelModule
import com.example.foodreciepesapp.di.modules.main.MainFragmentBuilderModule
import com.example.foodreciepesapp.di.modules.main.MainModule
import com.example.foodreciepesapp.di.modules.main.MainViewModelModule
import com.example.foodreciepesapp.ui.auth.AuthActivity
import com.example.foodreciepesapp.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class,AuthModule::class])
    abstract fun contributesAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class,MainViewModelModule::class,MainModule::class])
    abstract fun contributeMainActivity() : MainActivity

}