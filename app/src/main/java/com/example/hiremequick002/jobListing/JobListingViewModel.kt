package com.example.hiremequick002.jobListing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class JobListingViewModel(private val jobDao: JobDao): ViewModel() {

    val allJobListings: LiveData<List<JobListing>> = jobDao.getAllJobListings().asLiveData()


    // takes a player object and adds the data to the database in a non-blocking way
    private fun insertJobListingRecord(jobListing: JobListing) {

        viewModelScope.launch {
            jobDao.insertJobListing(jobListing)
        }
    }

    private fun getNewPlayerEntry(
        jobTitle: String,
        jobDescription: String
    ): JobListing {

        return JobListing(

            jobTitle = jobTitle,
            jobDescription = jobDescription
        )
    }

    fun addNewJobListing(
        jobTitle: String, jobDescription: String
    ) {
        val newJobListing = getNewPlayerEntry(jobTitle, jobDescription)
        insertJobListingRecord(newJobListing)
    }

    fun isEntryValid(
        jobTitle: String, jobDescription: String

    ) : Boolean {
        if (
            jobTitle.isBlank() || jobDescription.isBlank()
            ) {
            return false
        }
        return true
    }

    // function to retrieve player
    fun fetchJobListing(id: Int): LiveData<JobListing> {
        return jobDao.getJobListing(id).asLiveData()

    }




    // function to delete player
    fun deleteJobListing(jobListing: JobListing) {
        viewModelScope.launch {
            jobDao.deleteJobListing(jobListing)
        }
    }

    // function to retrieve player entry (private)



    //function to update  existing player
    fun updateJobListing(
//        playerId: Int,
//        playerName: String,
//        playerAge: String,
//        playerPosition: String,
//        playerValue: String
        jobId: Int,
        jobTitle: String,
        jobDescription: String
    ) {
//        val updatedPlayer = getUpdatedPlayerEntry(playerId, playerName, playerAge, playerPosition, playerValue)
//        updatePlayer(updatedPlayer)
        val updatedJobListing = getUpdatedJobListing(jobId,jobTitle, jobDescription)
        updateJobListing(updatedJobListing)

    }


    // private function to update player (private) - launches coroutine to update player in a non-blocking way
    private fun updateJobListing(jobListing: JobListing) {
        viewModelScope.launch {
            jobDao.updateJobListing(jobListing)
        }
    }

    /* function to retrieve updated player entry
    - returns an instance of the player entity class with player info updated by the user

     */
    private fun getUpdatedJobListing(
        jobId: Int,
        jobTitle: String,
        jobDescription: String
    ): JobListing {
        return JobListing(

            jobId = jobId,
            jobTitle = jobTitle,
            jobDescription = jobDescription
        )
    }


}

// a Factory class that instantiates objects/ InventoryViewModel instance that can respond to lifecycle events
class JobListingViewModelFactory(private val jobDao: JobDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobListingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobListingViewModel(jobDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}