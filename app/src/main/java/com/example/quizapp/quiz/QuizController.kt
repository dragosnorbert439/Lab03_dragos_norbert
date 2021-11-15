package com.example.quizapp.quiz

import android.content.Context
import com.example.quizapp.R
import java.io.*

class QuizController(var context: Context) {
    var questions: ArrayList<Question> = ArrayList()
    var indexer = 0

    init {
        if (!loadQuestions()) {
            throw FileNotFoundException("could not find file or file invalid")
        }
    }

    // randomize/shuffles the list
    public fun randomizeQuiz(): Unit {
        questions.forEach { it.answers.shuffle() }
        this.questions.shuffle()
    }

    private fun loadQuestions(): Boolean {
        return try {
            val isReader: InputStream = context.resources.openRawResource(R.raw.questionstxt)
            val reader = BufferedReader(InputStreamReader(isReader))
            val lines = reader.readLines()

            var textTemp = ""
            var answersTemp = arrayListOf<String>()
            var correctAnswerTemp = ""

            for (i in 0..(lines.size - 1)) {
                if (i % 5 == 0) {
                    textTemp = lines.get(i)
                } else {
                    if (i % 5 == 1) {
                        answersTemp.add(lines.get(i))
                        correctAnswerTemp = lines.get(i)
                    } else {
                        answersTemp.add(lines.get(i))
                    }
                    if (i % 5 == 4) {
                        questions.add(Question(textTemp, answersTemp, correctAnswerTemp))
                        answersTemp = arrayListOf<String>()
                    }
                }
            }
            true
        } catch (e: FileNotFoundException) {
            false
        }
    }
}





