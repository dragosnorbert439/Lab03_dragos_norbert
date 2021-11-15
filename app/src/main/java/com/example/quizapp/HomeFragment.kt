package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // TEST YOUR SKILLS BUTTON
        view.testYourSkillsButton.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_quizStartFragment)
        }

        // READ QUESTIONS BUTTON
        view.readQuestionsButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_questionListFragment)
        }

        // CREATE QUESTION BUTTON
        view.createQuestionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_questionAddFragment)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}