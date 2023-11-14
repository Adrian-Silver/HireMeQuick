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
import com.example.hiremequick002.databinding.FragmentAddJobListingBinding
import com.example.hiremequick002.jobListing.JobListing
import com.example.hiremequick002.jobListing.JobListingApplication
import com.example.hiremequick002.jobListing.JobListingViewModel
import com.example.hiremequick002.jobListing.JobListingViewModelFactory


class AddJobListingFragment : Fragment() {


    private val viewModel: JobListingViewModel by activityViewModels {
        JobListingViewModelFactory(
            (activity?.application as JobListingApplication).database.jobDao()
        )
    }

//    private val navigationArgs: JobListingDetailsFragmentArgs by navArgs()
    private val navigationArgs: AddJobListingFragmentArgs by navArgs()

    lateinit var jobListing: JobListing

    private var _binding: FragmentAddJobListingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddJobListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.jobTitleEt.text.toString(),
            binding.jobDescriptionEt.text.toString()
        )
    }

    // bind views with player info passed in
    private fun bind(jobListing: JobListing) {

        binding.apply {

            jobTitleEt.setText(jobListing.jobTitle)
            jobDescriptionEt.setText(jobListing.jobDescription)

            btnSaveJob.setOnClickListener {
                updateJobListing()
            }
        }
    }


    private fun addNewJobListing() {
        if (isEntryValid()) {
            viewModel.addNewJobListing(
                binding.jobTitleEt.text.toString(),
                binding.jobDescriptionEt.text.toString()

            )
            // after adding a new item, when button is clicked, will navigate to PlayerListFragment
            val action = AddJobListingFragmentDirections.actionAddJobListingFragmentToJobListFragment()
            findNavController().navigate(action)
        }
    }

    // Update Player Details
    private fun updateJobListing() {

        if (isEntryValid()) {
            viewModel.updateJobListing(
                this.navigationArgs.jobListingId,

                this.binding.jobTitleEt.text.toString(),
                this.binding.jobDescriptionEt.text.toString(),

            )

            val action = AddJobListingFragmentDirections.actionAddJobListingFragmentToJobListFragment()
            findNavController().navigate(action)
        }

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Functionality to retrieve and edit player details
        val id = navigationArgs.jobListingId

        if (id > 0) {
            viewModel.fetchJobListing(id).observe(this.viewLifecycleOwner) { selectedJobListing ->
                jobListing = selectedJobListing
                bind(jobListing)
            }
        } else {
            binding.btnSaveJob.setOnClickListener {
                addNewJobListing()
            }
        }
    }




}