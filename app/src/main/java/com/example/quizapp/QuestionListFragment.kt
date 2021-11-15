package com.example.quizapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.FragmentQuestionListBinding
import com.example.quizapp.quiz.QuestionAdapter

class QuestionListFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuestionListBinding
    private lateinit var adapter: QuestionAdapter
    val detailsViewModel: QuestionDetailsViewModel by activityViewModels()

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
        val view = inflater.inflate(R.layout.fragment_question_list, container, false)
        binding = FragmentQuestionListBinding.inflate(inflater)

        val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)

        detailsViewModel.setQuizController(viewModel.quizController)

        adapter = QuestionAdapter(viewModel.quizController, OnDetailsButtonListener(), OnDeleteButtonListener())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)


        return view
    }

    inner class OnDetailsButtonListener : QuestionAdapter.DetailsButtonOnClickListener {
        override fun onButtonClick(position: Int) {
            detailsViewModel.currentIndex = position
            findNavController().navigate(R.id.itemDetailsFragment)
        }
    }

    inner class OnDeleteButtonListener : QuestionAdapter.DeleteButtonOnClickListener {
        override fun onButtonClick(position: Int) {
            viewModel.quizController.questions.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionListFragment
    }
}