package com.example.quizapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quizapp.databinding.FragmentQuizEndBinding
import java.lang.StringBuilder

class QuizEndFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(QuizViewModel::class.java)
            builder = AlertDialog.Builder(this.activity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizEndBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // POINTS RESULT
        var points = 0
        var numQuestions = 0
        if (viewModel.quizController.questions.size < 4) numQuestions = viewModel.quizController.questions.size
        for (i in 0 until numQuestions) {
            if (viewModel.answeredQuestions[i]!! > -1) {
                if (viewModel.quizController.questions[i].rightAnswer==
                    viewModel.quizController.questions[i].answers[viewModel.answeredQuestions[i]!!]) {
                        ++points
                }
            }
        }
        if (points > viewModel.highScore) viewModel.highScore = points
        binding.quizPointsEndTV.text = StringBuilder(points.toString() + "/" + viewModel.quizController.indexer + " points")

        // TRY AGAIN BUTTON
        binding.tryAgainButton.setOnClickListener {
            viewModel.resetQuiz()
            Navigation.findNavController(view).navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {

                builder.setTitle("Quiz ended").setMessage("Do you want to exit the application or try again?")
                    .setNegativeButton("Exit") { _: DialogInterface, _: Int -> run { activity?.finish() } }
                    .setPositiveButton("Try again") { _: DialogInterface, _: Int -> run {
                        viewModel.resetQuiz()
                        /*
                        activity?.supportFragmentManager?.beginTransaction()?.apply {
                            replace(R.id.fragmentHolderFL, QuizStartFragment.newInstance())
                            commit()
                        }*/
                    } }
                    .setNeutralButton("Nothing") { _: DialogInterface, _: Int -> {} }
                builder.show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuizEndFragment()
    }
}