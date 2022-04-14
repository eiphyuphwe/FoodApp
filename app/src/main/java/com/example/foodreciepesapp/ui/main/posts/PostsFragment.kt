package com.example.foodreciepesapp.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodreciepesapp.R
import com.example.foodreciepesapp.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment(){

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var postsViewModel: PostsViewModel
    @Inject
    lateinit var adapter: PostsAdapter

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        postsViewModel = ViewModelProviders.of(this,providerFactory).get(PostsViewModel::class.java)

        initRecyclerView()
        postsViewModel.getPostsFromApi()
        subscribeObservers()

    }

    private fun subscribeObservers() {

        postsViewModel.getPosts().observe(viewLifecycleOwner) { listResource ->

            listResource?.let {
                when(listResource) {
                   is Resource.Loading -> {

                   }
                    is Resource.Success -> {
                        listResource.data?.let { adapter.setPosts(it) }

                    }

                    is Resource.Error -> {
                        listResource.message?.let { it1 -> Log.e("Posts", it1) }
                    }
                }

            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.HORIZONTAL))
        recyclerView.adapter = adapter
    }
}