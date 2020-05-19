package com.example.notepad.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.notepad.R
import com.example.notepad.ui.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
            viewModel.note.observe(this, Observer { note ->
                if (note != null) {
                    tvTitle.text = note.title
                    tvLastUpdated.text = getString(R.string.last_updated, note.lastUpdated.toString())
                    tvNote.text = note.text
                }
            }
        )
    }

    private fun initViews() {
        fab.setOnClickListener { editNote() }
    }

    /**
     * Open the edit note page
     */
    private fun editNote() {
        val intent = Intent(this, EditActivity::class.java)
        intent.putExtra(EditActivity.EXTRA_NOTE, viewModel.note.value)
        startActivity(intent)
    }

}
