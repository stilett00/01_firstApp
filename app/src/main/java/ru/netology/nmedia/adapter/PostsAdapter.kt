package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardLayoutBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.ui.PostClickHandler

typealias OnLikeListener = (post: Post) -> Unit
typealias OnShareListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            PostCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

}



class PostViewHolder(
    private val binding: PostCardLayoutBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {

    private val postClickHandler = PostClickHandler()

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            shareCount.text = postClickHandler.numbersConverter(post.share)
            viewsCount.text = postClickHandler.numbersConverter(post.views)

            if (post.likedByMe) {
                likes.setImageResource(R.drawable.ic_liked)
            } else {
                likes.setImageResource(R.drawable.ic_like)
            }

            likes.setOnClickListener {
                onLikeListener(post)
            }

            share.setOnClickListener {
                onShareListener(post)
            }

            likesCount.text = postClickHandler.numbersConverter(post.likes)
        }
    }
}
