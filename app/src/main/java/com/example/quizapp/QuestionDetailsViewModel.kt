package com.example.quizapp

import androidx.lifecycle.ViewModel
import com.example.quizapp.quiz.Question
import com.example.quizapp.quiz.QuizController

class QuestionDetailsViewModel : ViewModel() {
    private lateinit var qc : QuizController
    public var currentIndex = 0

    fun setQuizController(qc : QuizController) {
        this.qc = qc
    }

    fun getQuestion() : Question {
        return qc.questions[currentIndex]
    }
}