package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ViewDataBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentHolderFL, QuizStartFragment.newInstance()).commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (viewModel.quizController.indexer > 0) viewModel.quizController.indexer += -1
    }
}