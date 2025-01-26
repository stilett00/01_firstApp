package ru.netology.nmedia.ui

import kotlin.math.floor

class PostClickHandler {

    fun numbersConverter(number: Int): String {
        return when {
            number in 0..999 -> number.toString()
            number in 1000..9999 -> {
                val formatted = floor(number / 1000.0 * 10) / 10
                "%.1fK".format(formatted)
            }

            number in 10_000..999_999 -> {
                val formatted = floor(number / 1000.0)
                "%.0fK".format(formatted)
            }

            number >= 1_000_000 -> {
                val formatted = floor(number / 1_000_000.0 * 10) / 10
                "%.1fM".format(formatted)
            }

            else -> number.toString()
        }
    }
}