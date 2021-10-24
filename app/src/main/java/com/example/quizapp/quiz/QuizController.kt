package com.example.quizapp.quiz

import java.io.File
import java.io.FileNotFoundException

class QuizController()
{
    var questions: ArrayList<Question> = ArrayList<Question>()

    init
    {
        if(!loadQuestoins("./src/main/resources/testQuestions.csv")) throw FileNotFoundException("could not find file or file invalid")
    }

    // heart of the class
    fun doQuiz(numQuestions: Int): Unit
    {
        if (numQuestions < 1) return

        this.randomizeQuiz()

        var rightAnswers = 0
        var indexer = 0

        while (indexer < numQuestions + 1 && indexer < this.questions.size)
        {
            println("Question #$indexer")
            println(this.questions[indexer].question)

            for (i in 0 until questions[indexer].answers.size)
            {
                println("${i + 1}. ${questions[indexer].answers[i]}")
            }

            print("Your answer: ")
            var ans: String? = readLine()

            if (ans != null)
            {
                if (this.questions[indexer].answers[ans.toIntOrNull()!!].equals(this.questions[indexer].rightAnswer)) ++rightAnswers
            }

            println()
            ++indexer
        }

        println("\nTotal number questions: $indexer\tTotal number of correct answers: $rightAnswers")
    }

    // randomize/shuffles the list
    fun randomizeQuiz(): Unit { this.questions.shuffle() }

    private fun loadQuestoins(fileName: String): Boolean
    {
        return try
        {
            val myFile = File(fileName);

            val lines = myFile.readLines()

            myFile.forEachLine {
                val elements = it.split(",")
                var answers = ArrayList<String>()

                for (i in 1 .. elements.size - 2)
                {
                    answers.add(elements[i])
                }

                val newQuestion = Question(elements[0], answers, elements[elements.size - 2])
                this.questions.add(newQuestion)
            }
            true;
        }
        catch (e: FileNotFoundException) { false }
    }
}





