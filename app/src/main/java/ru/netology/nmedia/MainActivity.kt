package ru.netology.nmedia

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.floor


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            share = 1_110_000,
            views = 1200
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            shareCount.text = numbersConverter(post.share)
            viewsCount.text = numbersConverter(post.views)

            if (post.likedByMe) {
                likes.setImageResource(R.drawable.ic_liked)
            }
            likesCount.text = numbersConverter(post.likes)

            root.setOnClickListener {
                Log.d("MainActivity", "root clicked")
            }

            avatar.setOnClickListener {
                Log.d("MainActivity", "avatar clicked")
            }

            likes.setOnClickListener {
                Log.d("MainActivity", "like clicked")
                post.likedByMe = !post.likedByMe
                likes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked else R.drawable.ic_like
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likesCount.text = numbersConverter(post.likes)
            }

            share.setOnClickListener {
                Log.d("MainActivity", "share clicked")
                post.share++
                shareCount.text = numbersConverter(post.share)
            }
        }
    }

    private fun numbersConverter(number: Int): String {
        return when {
            number in 0..999 -> number.toString()
            number in 1000..9999 -> {
                val formatted = floor(number / 1000.0 * 10) / 10 // Округляем до одного знака после запятой
                "%.1fK".format(formatted)
            }
            number in 10_000..999_999 -> {
                val formatted = floor(number / 1000.0) // Округляем в меньшую сторону без дробной части
                "%.0fK".format(formatted)
            }
            number >= 1_000_000 -> {
                val formatted = floor(number / 1_000_000.0 * 10) / 10 // Округляем до одного знака после запятой
                "%.1fM".format(formatted)
            }
            else -> number.toString()
        }
    }
}
