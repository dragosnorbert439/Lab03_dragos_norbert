package com.example.quizapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quizapp.databinding.FragmentQuizStartBinding

class QuizStartFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var binding: FragmentQuizStartBinding
    private lateinit var builder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(QuizViewModel::class.java)
            builder = AlertDialog.Builder(this.activity)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // PLAYER NAME
        if (viewModel.userName != "Player Name") binding.personNameStart.setText(viewModel.userName)

        // CLEAR PREVIOUS QUESTION FRAGMENTS IF THEY EXIST
        for (i in 0 until activity?.supportFragmentManager?.backStackEntryCount!!)
           activity?.supportFragmentManager?.popBackStack()

        // START BUTTON
        binding.startButton.setOnClickListener {
            if (binding.personNameStart.length() > 0) {
                viewModel.userName = binding.personNameStart.text.toString()
                Navigation.findNavController(view).navigate(R.id.action_quizStartFragment_to_questionFragment)
            }
            else {
                builder.setTitle("Name field empty").setMessage("Please provide a name before continuing.")
                    .setPositiveButton("Ok") { _: DialogInterface, _: Int -> {} }
                builder.show()
            }
        }

        // CONTACTS BUTTON
        binding.contactsButton.setOnClickListener {

            val i = Intent(Intent.ACTION_PICK)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, 111)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true // default to enabled
        ) {
            override fun handleOnBackPressed() {
                builder.setTitle("").setMessage("Do you want to exit?")
                    .setPositiveButton("Yes") { alertDialog: DialogInterface, i: Int -> activity?.finish() }
                    .setNegativeButton("No") { alertDialog: DialogInterface, i: Int -> }
                builder.show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,  // LifecycleOwner
            callback
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuizStartFragment()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == Activity.RESULT_OK){
            val contactURI: Uri = data?.data?: return
            val cols: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val res: Cursor? = activity?.contentResolver?.query(contactURI, cols, null, null, null)
            if (res?.moveToFirst()!!){
                binding.personNameStart.setText(res.getString(0))
            }
        }
    }



}