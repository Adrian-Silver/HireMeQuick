package com.example.hiremequick002.jobListing

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobListing(
    @PrimaryKey(autoGenerate = true)
    val jobId: Int = 0,
    val jobTitle: String,
    val jobDescription: String
//    val jobRequirements: List<String>

)
