package com.example.foodreciepesapp.di.modules



import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.foodreciepesapp.R
import com.example.foodreciepesapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.color.white)
            .error(R.color.white)
    }

    @Singleton
    @Provides
     fun provideGlideInstance(application:Application, requestOptions : RequestOptions) : RequestManager{
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideAppDrawable(application: Application?): Drawable {
        return ContextCompat.getDrawable(application!!, R.drawable.logo)!!
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance() = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}