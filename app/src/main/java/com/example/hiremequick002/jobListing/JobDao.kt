package com.example.hiremequick002.jobListing

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertJobListing(jobListing: JobListing)

    @Update
    suspend fun updateJobListing(jobListing: JobListing)

    @Delete
    suspend fun deleteJobListing(jobListing: JobListing)

    @Query("DELETE FROM joblisting")
    suspend fun deleteAllJobListings()

    @Query("SELECT * from joblisting WHERE jobId = :id") // Retrieve a player's data based on their ID
    fun getJobListing(id: Int): Flow<JobListing>

    @Query("SELECT * from joblisting ORDER BY jobTitle ASC") // Retrieve all players and order them in ascending order based on their name.
    fun getAllJobListings(): Flow<List<JobListing>>
}