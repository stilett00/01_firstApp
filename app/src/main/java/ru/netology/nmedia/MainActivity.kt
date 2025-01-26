package ru.netology.nmedia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.ui.PostClickHandler
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postClickHandler = PostClickHandler()

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
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
                likesCount.text = postClickHandler.numbersConverter(post.likes)
            }
        }

        binding.avatar.setOnClickListener {
                Log.d("MainActivity", "avatar clicked")
            }
        
        binding.root.setOnClickListener {
            Log.d("MainActivity", "root clicked")
        }

        binding.likes.setOnClickListener {
            Log.d("MainActivity", "like clicked")
            viewModel.like()
        }

        binding.share.setOnClickListener {
            Log.d("MainActivity", "share clicked")
            viewModel.share()
        }
    }
}
