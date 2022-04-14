package com.example.foodreciepesapp.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodreciepesapp.SessionManager
import com.example.foodreciepesapp.model.Posts
import com.example.foodreciepesapp.network.main.MainApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel  @Inject constructor(
    val sessionManager: SessionManager,
    val mainApi: MainApi
): ViewModel() {

    private var _posts = MutableLiveData<Resource<List<Posts>>> ()
    val postsDataList : LiveData<Resource<List<Posts>>> = _posts

    fun getPostsFromApi(){
        _posts.value = Resource.Loading()

        sessionManager.getAuthUser()?.let {
            mainApi.getPostsFromUser(it.id)
                .onErrorReturn {
                    val post = Posts(-1,-1,"","")
                    val posts = mutableListOf<Posts>()
                    posts.add(post)
                    posts
                }
                .map { posts ->
                    if(posts.size > 0){
                        if(posts.get(0).id == -1){
                            Resource.Error("Something went wrong", null);
                        }
                    }

                    Resource.Success(posts);
                }
                .subscribeOn(Schedulers.io())
                .subscribe{
                    _posts.postValue(it)
                }
        }


    }


    fun getPosts() = postsDataList
}