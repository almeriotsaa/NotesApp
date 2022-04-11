package com.almerio.notesapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.almerio.notesapp.data.local.entity.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NotesDB : RoomDatabase(){
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        var instance: NotesDB? = null

        @JvmStatic
        fun getDatabase(context: Context) : NotesDB {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        NotesDB::class.java,
                        "notes.db"
                    ).build()
                }
            }
            return instance as NotesDB
        }
    }
}