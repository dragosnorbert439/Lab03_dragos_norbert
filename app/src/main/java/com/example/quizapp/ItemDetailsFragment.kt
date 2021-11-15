package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.quizapp.databinding.FragmentItemDetailsBinding
import kotlinx.android.synthetic.main.recycle_view_item.*

class ItemDetailsFragment() : Fragment() {
    private lateinit var binding : FragmentItemDetailsBinding

    lateinit var question: String
    lateinit var answers: ArrayList<String>

    val detailsViewModel : QuestionDetailsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemDetailsBinding.inflate(layoutInflater)

        question = detailsViewModel.getQuestion().question
        answers = detailsViewModel.getQuestion().answers

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemDetailsQuestion.text = question
        binding.itemDetailsAnswer1.text = answers[0]
        binding.itemDetailsAnswer2.text = answers[1]
        binding.itemDetailsAnswer3.text = answers[2]
        binding.itemDetailsAnswer4.text = answers[3]
    }

    companion object {
        @JvmStatic
        fun newInstance() = ItemDetailsFragment
    }
}