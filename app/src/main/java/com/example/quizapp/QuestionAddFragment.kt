package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.FragmentQuestionAddBinding
import com.example.quizapp.quiz.Question

class QuestionAddFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuestionAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(QuizViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentQuestionAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ADD QUESTION BUTTON
        binding.addQuestionButton.setOnClickListener {
            viewModel.quizController.questions.add(Question(
                binding.editTextQuestion.text.toString(),
                arrayListOf(
                    binding.editTextCorrectAnswer.text.toString(),
                    binding.editTextAnswer2.text.toString(),
                    binding.editTextAnswer3.text.toString(),
                    binding.editTextAnswer4.text.toString()
                ),
                binding.editTextCorrectAnswer.text.toString()
            ))
            Navigation.findNavController(view).navigate(R.id.action_questionAddFragment_self)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionAddFragment
    }
}