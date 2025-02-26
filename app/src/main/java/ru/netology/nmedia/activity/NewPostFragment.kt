package ru.netology.nmedia.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(layoutInflater, container, false)
        val viewModel: PostViewModel by activityViewModels()

        arguments?.textArg
            ?.let {
                binding.content.setText(it)
                arguments?.textArg = null
            }

        binding.content.requestFocus()
        binding.ok.setOnClickListener {
            if (!binding.content.text.isNullOrBlank()) {
                val text = binding.content.text.toString()
                viewModel.changeContent(text)
                viewModel.save()
            }
            findNavController().navigateUp()
        }

        return binding.root
    }


object NewPostContract : ActivityResultContract<String?, String?>() {

    override fun createIntent(context: Context, input: String?) = Intent(context, NewPostFragment::class.java).apply {
        putExtra(Intent.EXTRA_TEXT, input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?) = intent?.getStringExtra(Intent.EXTRA_TEXT)
    }
}