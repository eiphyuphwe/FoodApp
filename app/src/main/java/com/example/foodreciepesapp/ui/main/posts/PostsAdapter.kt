package com.example.foodreciepesapp.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodreciepesapp.R
import com.example.foodreciepesapp.model.Posts

class PostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts = listOf<Posts>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val view : View = LayoutInflater.from(parent.context).inflate(
           R.layout.layout_post_item,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
    }

    override fun getItemCount(): Int {
      return  posts.size
    }

    fun setPosts(posts:List<Posts>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val body: TextView = itemView.findViewById(R.id.body)

        fun bind(post: Posts) {
            title.setText(post.title)
            body.setText(post.body)
        }

    }

}