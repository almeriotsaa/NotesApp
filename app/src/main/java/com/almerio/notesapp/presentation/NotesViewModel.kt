package com.almerio.notesapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.almerio.notesapp.data.NotesRepository
import com.almerio.notesapp.data.local.entity.Notes
import com.almerio.notesapp.data.local.room.NotesDB
import com.almerio.notesapp.data.local.room.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao : NotesDao = NotesDB.getDatabase(application).notesDao()
    private val notesRepository : NotesRepository = NotesRepository(notesDao)

    val sortByHighPriority : LiveData<List<Notes>> = notesRepository.sortByHighPriority
    val sortByLowPriority : LiveData<List<Notes>> = notesRepository.sortByLowPriority

    fun getAllNotes() : LiveData<List<Notes>> = notesRepository.getAllNotes

    fun insertNotes(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insertNotes(note)
        }
    }

    fun searchNoteByQuery(searchQuery: String): LiveData<List<Notes>> {
        return notesDao.searchNoteByQuery(searchQuery)
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllData()
        }
    }

    fun deleteNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteNote(notes)
        }
    }

    fun updateNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.updateNote(notes)
        }
    }
}