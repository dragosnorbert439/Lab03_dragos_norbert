package com.example.quizapp.quiz

import android.content.Context
import com.example.quizapp.R
import java.io.*

class QuizController(var context: Context) {
    var questions: ArrayList<Question> = ArrayList()
    var indexer = 0

    init {
        if(!loadQuestions()) {
            throw FileNotFoundException("could not find file or file invalid")
        }
        // randomizeQuiz()
    }

    // randomize/shuffles the list
    public fun randomizeQuiz(): Unit {
        questions.forEach { it.answers.shuffle() }
        this.questions.shuffle()
    }

    private fun loadQuestions(): Boolean {
        return try {
            val isReader: InputStream = context.resources.openRawResource(R.raw.questions)
            val reader = BufferedReader(InputStreamReader(isReader))

            reader.forEachLine {
                val elements = it.split(",")
                val answers = ArrayList<String>()

                for (i in 1 .. elements.size - 2) {
                    answers.add(elements[i])
                }

                val newQuestion = Question(elements[0], answers, elements.last())
                this.questions.add(newQuestion)
            }
            true;
        }
        catch (e: FileNotFoundException) { false }
    }
}





