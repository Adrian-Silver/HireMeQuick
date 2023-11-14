package com.example.hiremequick002.jobListing.jobFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiremequick002.R
import com.example.hiremequick002.databinding.FragmentJobListBinding
import com.example.hiremequick002.jobListing.JobListingAdapter
import com.example.hiremequick002.jobListing.JobListingApplication
import com.example.hiremequick002.jobListing.JobListingViewModel
import com.example.hiremequick002.jobListing.JobListingViewModelFactory


class JobListFragment : Fragment() {

    private val viewModel: JobListingViewModel by activityViewModels {
        JobListingViewModelFactory(
            (activity?.application as JobListingApplication).database.jobDao()
        )
    }

    private var _binding: FragmentJobListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJobListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // to navigate from PlayerListFragment to PlayerDetailsFragment -> use the adapter
        val adapter = JobListingAdapter {
            val action = JobListFragmentDirections.actionJobListFragmentToJobListingDetailsFragment(it.jobId)
            this.findNavController().navigate(action)
        }

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.allJobListings.observe(this.viewLifecycleOwner) { players ->
            players.let {
                adapter.submitList(it)
            }

        }


//        // To update list view
//        GlobalScope.launch(Dispatchers.IO) {
//            adapter.submitList(viewModel.addNewPlayer())
//        }


        binding.floatingActionButton.setOnClickListener {
            val action = JobListFragmentDirections.actionJobListFragmentToAddJobListingFragment(
                getString(R.string.add_job_listing_title)
            )
            this.findNavController().navigate(action)
        }

    }


}