package com.example.hiremequick002.employer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hiremequick002.databinding.ActivityEmployerProfileBinding
import com.example.hiremequick002.jobListing.PostJobActivity

class EmployerProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployerProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployerProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.employerDetailsCompleteButton.setOnClickListener {
            saveEmployerData()

            val intent = Intent(this, PostJobActivity::class.java)
            startActivity(intent)
        }


    }

    private fun saveEmployerData() {
        val employerEmail = binding.emailEt.text.toString()
        val username = binding.usernameEt.text.toString()
        val firstName = binding.firstNameEt.text.toString()
        val lastName = binding.lastNameEt.text.toString()
        val companyName = binding.companyNameEt.text.toString()

//        val bloodGroup = binding.spinnerBloodType.selectedItem.toString()

//        val bloodGroup = binding.spinnerBloodType.selectedItem.toString()
//        binding.spinnerBloodType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                val bloodGroup = parent?.getItemAtPosition(position).toString()
//                // do something with selected blood group
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // do something when nothing is selected
//            }
//        }


        val employer: HashMap<kotlin.String, kotlin.Any> = kotlin.collections.hashMapOf(
            "email" to employerEmail,
            "username" to username,
            "firstname" to firstName,
            "lastName" to lastName,
            "companyName" to companyName
        )



        val email = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.email
        val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()

        if (email != null) {
            db.collection("employers")
                .document(email)
                .set(employer)
                .addOnSuccessListener {
                    android.widget.Toast.makeText(this, "User data saved successfully", android.widget.Toast.LENGTH_SHORT).show()
                    Log.d("EmployerProfileActivity","Employer Data Successfully Saved")
                }
                .addOnFailureListener {
                    android.widget.Toast.makeText(this, "Failed to save user data", android.widget.Toast.LENGTH_SHORT).show()
                    Log.d("EmployerProfileActivity","Failed to save Employer Data")

                }
        }
    }

}