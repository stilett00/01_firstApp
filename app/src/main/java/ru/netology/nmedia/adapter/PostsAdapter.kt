package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardLayoutBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.ui.PostClickHandler

interface OnInteractionListener {
    fun onLike(post: Post)
    fun onShare(post: Post)
    fun onRemove(post: Post)
    fun onEdit(post: Post)
    fun onPlayVideo(post: Post)
    fun onPostTapped(post: Post) {}
}

class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            PostCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.bind(getItem(position))
    }
}


class PostViewHolder(
    private val binding: PostCardLayoutBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    private val postClickHandler = PostClickHandler()

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            share.text = postClickHandler.numbersConverter(post.share)
            views.text = postClickHandler.numbersConverter(post.views)

            likes.isChecked = post.likedByMe
            likes.text = postClickHandler.numbersConverter(post.likes)

            likes.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            root.setOnClickListener {
                onInteractionListener.onPostTapped(post)
            }

            menu.setOnClickListener{
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_menu)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }

            if (post.videoUrl != null && post.videoUrl.isNotEmpty()) {
                videoThumbnail.visibility = View.VISIBLE
                playButton.visibility = View.VISIBLE

                videoThumbnail.setOnClickListener {
                    onInteractionListener.onPlayVideo(post)
                }

                playButton.setOnClickListener {
                    onInteractionListener.onPlayVideo(post)
                }
            } else {
                videoThumbnail.visibility = View.GONE
                playButton.visibility = View.GONE
            }
        }
    }
}
