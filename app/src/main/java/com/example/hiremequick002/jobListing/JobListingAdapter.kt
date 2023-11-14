package com.example.hiremequick002.jobListing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hiremequick002.databinding.JobListingItemBinding

class JobListingAdapter(
    private val onItemClicked: (JobListing) -> Unit) :
    ListAdapter<JobListing, JobListingAdapter.JobListingViewHolder>(DiffCallback
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JobListingViewHolder {
        return JobListingViewHolder(
            JobListingItemBinding.inflate(LayoutInflater.from(parent.context))
        )    }

    override fun onBindViewHolder(holder: JobListingViewHolder, position: Int) {

        val currentItem = getItem(position)

        // itemView inatoka wapi ???
        holder.itemView.setOnClickListener {
            onItemClicked(currentItem)
        }
        holder.bind(currentItem)
    }

    class JobListingViewHolder(private val binding: JobListingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(jobListing: JobListing) {
            binding.jobTitleEt.text = jobListing.jobTitle
            binding.jobDescriptionEt.text = jobListing.jobDescription
        }
    }

    companion object {
        //        private val diffUtil: DiffUtil<DiffUtil.ItemCallback<Item>()>)
        // defines how to compute if two words are the same or if the contents are same
        private val DiffCallback =
            object : DiffUtil.ItemCallback<JobListing>() {
                override fun areItemsTheSame(oldItem: JobListing, newItem: JobListing): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: JobListing, newItem: JobListing): Boolean {
                    return oldItem.jobTitle == newItem.jobTitle
                }

            }
    }

}