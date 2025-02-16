package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val initialText = intent.getStringExtra(Intent.EXTRA_TEXT)

        if (!initialText.isNullOrBlank()) {
            binding.content.setText(initialText)
        }

        binding.ok.setOnClickListener {
            val text = binding.content.text.toString()

            if (text.isBlank() && initialText == null) {
                setResult(RESULT_CANCELED)
            } else {
                setResult(RESULT_OK, Intent().apply { putExtra(Intent.EXTRA_TEXT, text) })
            }
            finish()
    }
}


object NewPostContract : ActivityResultContract<String?, String?>() {

    override fun createIntent(context: Context, input: String?) = Intent(context, NewPostActivity::class.java).apply {
        putExtra(Intent.EXTRA_TEXT, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)
    }
}