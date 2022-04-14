package com.example.foodreciepesapp.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.foodreciepesapp.ui.main.MainActivity
import com.example.foodreciepesapp.R
import com.example.foodreciepesapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), OnClickListener {


    @Inject
    lateinit var logo: Drawable
    @Inject
    lateinit var requestManager: RequestManager
    lateinit var imageView: ImageView
    lateinit var btnLogin: Button
    lateinit var edtUserId: EditText
    lateinit var progress_bar : ProgressBar
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthViewModel
    private var isVisibleProgressBar : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        imageView = findViewById(R.id.login_logo)
        btnLogin = findViewById(R.id.login_button)
        edtUserId = findViewById(R.id.user_id_input)
        progress_bar = findViewById(R.id.progress_bar)
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        setUpLogo()
        btnLogin.setOnClickListener(this)

        subscribeObserver()
    }

    private fun subscribeObserver() {

        viewModel.observeUser().observe(this, {

            when (it) {

                is AuthResource.Loading -> {
                    isVisibleProgressBar = true

                }
                is AuthResource.Authenticated -> {
                    Toast.makeText(this,it.data?.email+"",Toast.LENGTH_LONG).show()
                   loginSuccess()
                }
                is AuthResource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                is AuthResource.NotAuthenticated -> {
                    Toast.makeText(this, "Not authenticate", Toast.LENGTH_LONG).show()
                }

            }
        })
    }

    private fun setUpLogo() {

        requestManager
            ?.load(logo)
            ?.into(imageView)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_button -> {
                attemptLogin()
            }

        }
    }

    private fun attemptLogin() {

        viewModel.authenticationWithId((edtUserId.text.toString().toInt()))
    }

    private fun loginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showProgressBar() {
        if(isVisibleProgressBar) {
            progress_bar.isVisible = true
        }else{
            progress_bar.isVisible = false
        }
    }

}