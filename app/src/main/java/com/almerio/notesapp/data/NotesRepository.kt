package com.almerio.notesapp.data

import androidx.lifecycle.LiveData
import com.almerio.notesapp.data.local.entity.Notes
import com.almerio.notesapp.data.local.room.NotesDao

class NotesRepository(private val notesDao: NotesDao) {

    val getAllNotes: LiveData<List<Notes>> = notesDao.getAllNotes()
    val sortByHighPriority : LiveData<List<Notes>> = notesDao.sortByHighPriority()
    val sortByLowPriority : LiveData<List<Notes>> = notesDao.sortByLowPriority()

    suspend fun insertNotes(notes: Notes) {
        notesDao.addNote(notes)
    }

    fun searchNoteByQuery(searchQuery: String): LiveData<List<Notes>> {
        return notesDao.searchNoteByQuery(searchQuery)
    }

    suspend fun deleteAllData() {
        notesDao.deleteAllData()
    }

    suspend fun deleteNote(notes: Notes) {
        notesDao.deleteNote(notes)
    }

    suspend fun updateNote(notes: Notes) {
        notesDao.updateNote(notes)
    }
}