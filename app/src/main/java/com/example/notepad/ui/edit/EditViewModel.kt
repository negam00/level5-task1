package com.example.notepad.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notepad.database.NoteRepository
import com.example.notepad.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditViewModel (application: Application) :AndroidViewModel(application){

    private val noteRepo = NoteRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val note = MutableLiveData<Note?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    fun updateNote() {
        if (isNoteValid()) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    note.value?.let { noteRepo.updateNotepad(it) }
                }
                success.value = true
            }
        }

    }

    private fun isNoteValid () : Boolean {
        return when {
            note.value == null -> {
                error.value = "Note cannot be empty"
                false
            }
            note.value!!.title.isBlank() -> {
                error.value = "Title cannot be empty"
                false
            }
            else -> true
        }
    }
}