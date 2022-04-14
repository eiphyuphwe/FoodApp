package com.example.foodreciepesapp.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.foodreciepesapp.R
import com.example.foodreciepesapp.model.User
import com.example.foodreciepesapp.ui.auth.AuthResource
import com.example.foodreciepesapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ProfileFragment : DaggerFragment(){

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: ProfileViewModel
    private lateinit var email : TextView
    private lateinit var username:TextView
    private lateinit var website: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false);    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(
            ProfileViewModel::class.java
        )
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        website = view.findViewById(R.id.website)
        subscribeUser()
    }

    private fun subscribeUser(){
       // viewModel.getAuthenticatedUser().removeObservers(this)

        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner) { authResource ->
           Log.e("Auth",authResource.toString()+"")
            authResource?.let {

                when(authResource) {

                    is AuthResource.Authenticated -> {
                        setData(authResource.data)
                    }
                    is AuthResource.Error -> {

                        authResource.message?.let { it1 -> setErrorDetails(it1) }
                    }
                }
            }

        }
    }

    private fun setData(user: User?) {
        user?.let {
            email.text = it.email
            username.text = it.username
            website.text = it.website

        }
    }

    private fun setErrorDetails(message: String) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }


}