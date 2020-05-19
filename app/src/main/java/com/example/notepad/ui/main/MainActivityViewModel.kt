package com.example.notepad.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepad.database.NoteRepository
import com.example.notepad.model.Note

class MainActivityViewModel(application: Application) :AndroidViewModel(application) {
    private val noteRepo = NoteRepository(application.applicationContext)
    
    val note: LiveData<Note> = noteRepo.getNotepad()
}