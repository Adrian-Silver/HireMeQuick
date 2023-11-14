package com.example.hiremequick002

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.view.View
import android.widget.Toast
import com.example.hiremequick002.databinding.ActivityProfileTypeBinding
import com.example.hiremequick002.employee.ui.EmployeeProfileActivity
import com.example.hiremequick002.employer.EmployerProfileActivity

class ProfileTypeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileTypeBinding

    private lateinit var profileTypeSpinner: Spinner
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.profile_types_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            binding.profileTypeSpinner.adapter = adapter
        }

        // Set a listener for the spinner item selection
        binding.profileTypeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Handle selection
                    val selectedProfileType = parent?.getItemAtPosition(position).toString()
                    // You can use this value for further processing or validation
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                    Toast.makeText(this@ProfileTypeActivity, "Please Select Relevant Group", Toast.LENGTH_SHORT).show()

                }
            }

        // Set up the "Next" button click listener
        binding.nextButton.setOnClickListener {
            val selectedProfileType = binding.profileTypeSpinner.selectedItem.toString()

            when (selectedProfileType) {
                "Employee" -> {
                    val intent = Intent(this, EmployeeProfileActivity::class.java)
                    startActivity(intent)
                }
                "Employer" -> {
                    val intent = Intent(this, EmployerProfileActivity::class.java)
                    startActivity(intent)
                }
                // Add more cases if you have additional profile types
                else -> {
                    // Handle unexpected selection
                }
            }
        }
//
//        val selectedProfileType = getSelectedProfileType().toString()
//
//        if (selectedProfileType == "employer") {
////            startActivity(Intent(this, EmployerProfileActivity::class.java))
//            // In ProfileTypeActivity
//            val intent = Intent(this, EmployerProfileActivity::class.java)
//            intent.putExtra("profileType", "Employer")
//            startActivity(intent)
//
//        } else {
////            startActivity(Intent(this, EmployeeProfileActivity::class.java))
//            // In ProfileTypeActivity
//            val intent = Intent(this, EmployeeProfileActivity::class.java)
//            intent.putExtra("profileType", "Employee")
//            startActivity(intent)
//
//        }

    }

//    fun getSelectedProfileType() {
//
//
//
//    }

}