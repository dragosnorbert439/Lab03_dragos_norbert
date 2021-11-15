package com.example.quizapp

import android.app.Application
import com.example.quizapp.quiz.QuizController
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context by lazy { this.getApplication<Application>().applicationContext }
    val quizController = QuizController(context)
    val answeredQuestions = mutableMapOf<Int, Int>()
    var userName: String = "Player Name"
    var highScore: Int = 0

    fun resetQuiz() {
        quizController.indexer = 0
        quizController.randomizeQuiz()
        answeredQuestions.clear()
    }
}