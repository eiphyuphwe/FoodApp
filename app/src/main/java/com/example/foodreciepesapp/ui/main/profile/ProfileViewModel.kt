package com.example.foodreciepesapp.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.foodreciepesapp.SessionManager
import com.example.foodreciepesapp.model.User
import com.example.foodreciepesapp.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val sessionManager: SessionManager): ViewModel() {

    public fun getAuthenticatedUser() : LiveData<AuthResource<User>>{
        val data =  sessionManager.getAuthUserLiveData();
        val dataItem = data.value?.data
        return data
    }
}