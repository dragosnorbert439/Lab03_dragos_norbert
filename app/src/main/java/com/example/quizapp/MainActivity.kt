package com.example.quizapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.quiz.QuestionAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewModel: QuizViewModel
    private lateinit var drawer: DrawerLayout
    private val detailViewModel: QuestionDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ViewDataBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        drawer = findViewById(R.id.drawer_layout)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.bringToFront()

        val toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        drawer.bringToFront()
        drawer.requestLayout()
    }

    override fun onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            if (viewModel.quizController.indexer > 0) viewModel.quizController.indexer += -1
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                drawer.closeDrawer(GravityCompat.START)
                findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
            }
            R.id.nav_listofquestions -> {
                drawer.closeDrawer(GravityCompat.START)
                findNavController(R.id.fragmentContainerView).navigate(R.id.questionListFragment)
            }
            R.id.nav_profile -> {
                drawer.closeDrawer(GravityCompat.START)
                findNavController(R.id.fragmentContainerView).navigate(R.id.profileFragment)
            }
            R.id.nav_newquestion -> {
                drawer.closeDrawer(GravityCompat.START)
                findNavController(R.id.fragmentContainerView).navigate(R.id.questionAddFragment)
            }
            R.id.nav_quiztime -> {
                drawer.closeDrawer(GravityCompat.START)
                findNavController(R.id.fragmentContainerView).navigate(R.id.quizStartFragment)
            }
        }

        return true
    }
}