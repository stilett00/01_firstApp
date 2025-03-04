package ru.netology.nmedia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val share: Int = 0,
    val likedByMe: Boolean = false,
    val views: Int = 0,
    val videoUrl: String? = null,
) {
    fun toDto() = Post(
        id,
        author,
        content,
        published,
        likes,
        share,
        likedByMe,
        views,
        videoUrl)

    companion object {
        fun fromDto(post: Post) = PostEntity(
            post.id,
            post.author,
            post.content,
            post.published,
            post.likes,
            post.share,
            post.likedByMe,
            post.views,
            post.videoUrl
        )
    }
}

