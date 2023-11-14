package com.example.hiremequick002.jobListing.jobFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hiremequick002.R
import com.example.hiremequick002.databinding.FragmentJobListingDetailsBinding
import com.example.hiremequick002.jobListing.JobListing
import com.example.hiremequick002.jobListing.JobListingApplication
import com.example.hiremequick002.jobListing.JobListingViewModel
import com.example.hiremequick002.jobListing.JobListingViewModelFactory


class JobListingDetailsFragment : Fragment() {

    private val navigationArgs: JobListingDetailsFragmentArgs by navArgs()
    lateinit var jobListing: JobListing

    private val viewModel: JobListingViewModel by activityViewModels {
        JobListingViewModelFactory(
            (activity?.application as JobListingApplication).database.jobDao()
        )
    }

    private var _binding: FragmentJobListingDetailsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobListingDetailsBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.jobListingId

        viewModel.fetchJobListing(id).observe(this.viewLifecycleOwner) { selectedJobListing ->
            jobListing = selectedJobListing
            bind(jobListing)

        }

//        binding.btnPlayerEdit.setOnClickListener {
//            val action = PlayerDetailsFragmentDirections.actionPlayerDetailsFragmentToAddPlayerFragment(
//                getString(R.string.add_player_title))
//            this.findNavController().navigate(action)
//        }
//
//        binding.btnPlayerDelete.setOnClickListener {
//            deletePlayer()
//        }



    }

    private fun bind(jobListing: JobListing) {

        binding.apply {
            jobTitleDetailEtv.text = jobListing.jobTitle
            jobDescriptionDetailEtv.text = jobListing.jobDescription

            btnPlayerEdit.setOnClickListener { editJobListing() }
            btnPlayerDelete.setOnClickListener { deleteJobListing() }
        }

    }

    private fun deleteJobListing() {
        // Delete player details
        viewModel.deleteJobListing(jobListing)

        // Navigate back to list of players fragment
        findNavController().navigateUp()

    }

    private fun editJobListing() {

        val action = JobListingDetailsFragmentDirections.actionJobListingDetailsFragmentToAddJobListingFragment(
            "Edit Player Details",
            jobListing.jobId
        )
        this.findNavController().navigate(action)

    }


}