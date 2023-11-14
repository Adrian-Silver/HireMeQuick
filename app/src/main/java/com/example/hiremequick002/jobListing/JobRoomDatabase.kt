package com.example.hiremequick002.jobListing

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [JobListing::class], version = 1, exportSchema = false)
public abstract class JobRoomDatabase: RoomDatabase() {

    abstract fun jobDao(): JobDao

    companion object {
        // INSTANCE - Keeps a reference to the database, when one has been created.
        // this helps maintain a single instance of the database opened at a given time

        @Volatile //a volatile variable is never cached (all read & writes done to and from memory)
        private var INSTANCE: JobRoomDatabase? = null

        fun getDatabase(context: Context): JobRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobRoomDatabase::class.java,
                    "jobListing_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

//                return instance
                instance
            }
        }

    }
}