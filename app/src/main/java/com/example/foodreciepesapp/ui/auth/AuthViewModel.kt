package com.example.foodreciepesapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodreciepesapp.SessionManager
import com.example.foodreciepesapp.model.User
import com.example.foodreciepesapp.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AuthViewModel @Inject constructor(authApi: AuthApi, val sessionManager: SessionManager) : ViewModel() {

    private lateinit var authApi: AuthApi

    companion object {
        var TAG = "AuthViewModel"
    }

    init {
        this.authApi = authApi

    }

    fun authenticationWithId( userId: Int)
    {

        authApi.getUsers(userId)
            .onErrorReturn {
                val errorUser = User()
                errorUser.id = -1
                errorUser
            }
            .map {
                if(it.id == -1)
                    return@map AuthResource.Error("Could not authenticate")
                else
                    return@map AuthResource.Authenticated(it)
            }
            .subscribeOn(Schedulers.io())
            .subscribe { authResourceUser ->
                //_authUser.postValue(it)
                sessionManager.authenticateWithId(authResourceUser)
            }

    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUserLiveData()
    }

}