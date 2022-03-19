package com.example.foodreciepesapp.di.component

import android.app.Application
import com.example.foodreciepesapp.BaseApplication
import com.example.foodreciepesapp.di.modules.AndroidBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

//component must be interface or abstract
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidBuilderModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}