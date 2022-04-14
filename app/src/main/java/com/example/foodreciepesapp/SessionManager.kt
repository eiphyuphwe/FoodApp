package com.example.foodreciepesapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodreciepesapp.model.User
import com.example.foodreciepesapp.ui.auth.AuthResource
import com.google.gson.Gson
import javax.inject.Inject


class SessionManager @Inject constructor(val context: Context) {

    private val TAG = "Dagger Example"



    private val _catchedUser : MutableLiveData<AuthResource<User>> = MutableLiveData<AuthResource<User>>()
    private val catchUser : LiveData<AuthResource<User>> = _catchedUser

    public fun authenticateWithId(source:AuthResource<User>) {

        _catchedUser.postValue(AuthResource.Loading())
        if(source.data?.id == -1) {
            _catchedUser.postValue(AuthResource.NotAuthenticated("User is not authenticated"))
        }else {

            _catchedUser.postValue(source)

        }
        source?.data?.let { saveUserInPref(it) }

    }

     fun getAuthUserLiveData():LiveData<AuthResource<User>> {

        val user = getUserFromPref()
        if(user!=null) {
            if (user.id == -1) {
                _catchedUser.postValue(AuthResource.NotAuthenticated("User is not authenticated"))
            } else {
                _catchedUser.postValue(AuthResource.Authenticated(user))
            }
        }


        return _catchedUser
    }

    fun getAuthUser() : User? =  getUserFromPref()

   private val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"
   private val mPrefs: SharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME,  MODE_PRIVATE)

    private fun saveUserInPref(user: User) {
        val prefEditor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        prefEditor.putString("User",json)
        prefEditor.commit()

    }

     private fun getUserFromPref() : User? {
        val gson = Gson()
        val json = mPrefs.getString("User","")
        val user = gson.fromJson(json,User::class.java)
        return user
    }

}