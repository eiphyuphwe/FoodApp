package com.example.foodreciepesapp.network.main

import com.example.foodreciepesapp.model.Posts
import com.example.foodreciepesapp.model.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPostsFromUser(
       @Query("userId")
       id: Int
    ) : Flowable<List<Posts>>
}