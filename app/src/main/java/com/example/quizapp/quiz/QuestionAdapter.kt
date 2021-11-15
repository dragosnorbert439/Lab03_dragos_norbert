package com.example.quizapp.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.QuizViewModel
import com.example.quizapp.R

class QuestionAdapter(var qc: QuizController,
                      val detailsButtonListener: DetailsButtonOnClickListener,
                      val deleteButtonListener: DeleteButtonOnClickListener
                      ) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    private lateinit var viewModel: QuizViewModel

    interface DetailsButtonOnClickListener {
        fun onButtonClick(position: Int)
    }

    interface DeleteButtonOnClickListener {
        fun onButtonClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionView : TextView = itemView.findViewById(R.id.question)
        val answer1View : TextView = itemView.findViewById(R.id.correctAnswer)
        val detailsButton : Button = itemView.findViewById(R.id.detailsButton)
        val deleteButton : Button = itemView.findViewById(R.id.deleteButton)

        init {
            detailsButton.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    detailsButtonListener.onButtonClick(position)
                }
            }

            deleteButton.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteButtonListener.onButtonClick(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_view_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.questionView.text = qc.questions[position].question
        holder.answer1View.text = qc.questions[position].rightAnswer
    }

    override fun getItemCount(): Int {
        return qc.questions.size
    }
}