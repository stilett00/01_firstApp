package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.FragmentNewPostBinding
import ru.netology.nmedia.util.StringArg
import ru.netology.nmedia.viewmodel.PostViewModel

class NewPostFragment : Fragment() {

    val viewModel: PostViewModel by activityViewModels()

    companion object {
        var Bundle.postContent by StringArg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(layoutInflater, container, false)

        arguments?.postContent?.let {
                binding.content.setText(it)
                arguments?.postContent = null
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
}