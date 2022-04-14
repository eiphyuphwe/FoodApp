package com.example.foodreciepesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.foodreciepesapp.ui.auth.AuthActivity
import com.example.foodreciepesapp.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {
    companion object {
        private const val TAG = "Dagger Example"
    }

    @Inject
    lateinit var sessionManager:SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {

        sessionManager.getAuthUserLiveData().observe(this) {
            it?.let {
                when (it) {

                    is AuthResource.Loading -> {
                        Log.d(TAG, "onChanged: BaseActivity: LOADING...");

                    }

                    is AuthResource.Authenticated -> {
                        Log.d(
                            TAG, "onChanged: BaseActivity: AUTHENTICATED... " +
                                    "Authenticated as: " + it.data?.username
                        );
                    }

                    is AuthResource.Error -> {
                        Log.d(TAG, "onChanged: BaseActivity: ERROR...");
                    }

                    is AuthResource.NotAuthenticated -> {

                        navToLoginScreen();
                    }


                }
            }

        }

    }

    private fun navToLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}