package com.example.quizapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quizapp.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuestionBinding
    private var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(QuizViewModel::class.java)
            index = viewModel.quizController.indexer
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentQuestionBinding.inflate(layoutInflater)

        binding.questionHolder.text = viewModel.quizController.questions[index].question

        when (viewModel.answeredQuestions[index]) {
            0 -> binding.radioButtonAns1.isChecked = true
            1 -> binding.radioButtonAns2.isChecked = true
            2 -> binding.radioButtonAns3.isChecked = true
            3 -> binding.radioButtonAns4.isChecked = true
            else -> {
                viewModel.answeredQuestions[index] = -1
                binding.radioButtonAns1.isChecked = false
                binding.radioButtonAns2.isChecked = false
                binding.radioButtonAns3.isChecked = false
                binding.radioButtonAns4.isChecked = false
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // SETTING UP THE ANSWERS AND QUESTION
        binding.questionHolder.text = viewModel.quizController.questions[index].question
        binding.radioButtonAns1.text = viewModel.quizController.questions[index].answers[0]
        binding.radioButtonAns2.text = viewModel.quizController.questions[index].answers[1]
        binding.radioButtonAns3.text = viewModel.quizController.questions[index].answers[2]
        binding.radioButtonAns4.text = viewModel.quizController.questions[index].answers[3]

        // RADIO BUTTON 1
        binding.radioButtonAns1.setOnClickListener {
            binding.radioButtonAns2.isChecked = false
            binding.radioButtonAns3.isChecked = false
            binding.radioButtonAns4.isChecked = false
            viewModel.answeredQuestions[index] = 0
        }

        // RADIO BUTTON 2
        binding.radioButtonAns2.setOnClickListener {
            binding.radioButtonAns1.isChecked = false
            binding.radioButtonAns3.isChecked = false
            binding.radioButtonAns4.isChecked = false
            viewModel.answeredQuestions[index] = 1
        }

        // RADIO BUTTON 3
        binding.radioButtonAns3.setOnClickListener {
            binding.radioButtonAns2.isChecked = false
            binding.radioButtonAns1.isChecked = false
            binding.radioButtonAns4.isChecked = false
            viewModel.answeredQuestions[index] = 2
        }

        // RADIO BUTTON 4
        binding.radioButtonAns4.setOnClickListener {
            binding.radioButtonAns2.isChecked = false
            binding.radioButtonAns3.isChecked = false
            binding.radioButtonAns1.isChecked = false
            viewModel.answeredQuestions[index] = 3
        }

        // NEXT/SUBMIT BUTTON
        if (index + 1 == viewModel.quizController.questions.size) {
            binding.nextSubmitButton.text = "SUBMIT"
        }

        binding.nextSubmitButton.setOnClickListener {
            viewModel.quizController.indexer += 1
            if (viewModel.quizController.indexer == viewModel.quizController.questions.size
                || viewModel.quizController.indexer == 4) {
                Navigation.findNavController(view).navigate(R.id.action_questionFragment_to_quizEndFragment)
            }
            else {
                Navigation.findNavController(view).navigate(R.id.action_questionFragment_self)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = QuestionFragment()
    }
}