package com.example.hiremequick002.jobListing

import android.app.Application

class JobListingApplication: Application() {

    val database: JobRoomDatabase by lazy { JobRoomDatabase.getDatabase(this)}
}