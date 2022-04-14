package com.example.foodreciepesapp.network.auth

import com.example.foodreciepesapp.model.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUsers(
       @Path("id")
       id: Int
    ) : Flowable<User>
}