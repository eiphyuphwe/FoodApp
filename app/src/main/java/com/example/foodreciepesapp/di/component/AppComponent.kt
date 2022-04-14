package com.example.foodreciepesapp.di.component

import android.app.Application
import com.example.foodreciepesapp.BaseApplication
import com.example.foodreciepesapp.SessionManager
import com.example.foodreciepesapp.di.modules.ActivityBuilderModule
import com.example.foodreciepesapp.di.modules.AppModule
import com.example.foodreciepesapp.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


//component must be interface or abstract
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager?

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}