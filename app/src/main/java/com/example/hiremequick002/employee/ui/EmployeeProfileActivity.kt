package com.example.hiremequick002.employee.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hiremequick002.R
import com.example.hiremequick002.databinding.ActivityEmployeeProfileBinding
import com.example.hiremequick002.databinding.ActivityEmployerProfileBinding

class EmployeeProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeeProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.employeeDetailsCompleteButton.setOnClickListener {
            saveEmployerData()

            val intent = Intent(this, JobListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun saveEmployerData() {
        val employerEmail = binding.emailEt.text.toString()
        val username = binding.usernameEt.text.toString()
        val firstName = binding.firstNameEt.text.toString()
        val lastName = binding.lastNameEt.text.toString()
        val education = binding.educationEt.text.toString()
        val experience = binding.experienceEt.text.toString()
        val skills = binding.skillsEt.text.toString()
        val previousWorkplace = binding.previousWorkplaceEt.text.toString()

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


        val employee: HashMap<kotlin.String, kotlin.Any> = kotlin.collections.hashMapOf(
            "email" to employerEmail,
            "username" to username,
            "firstname" to firstName,
            "lastName" to lastName,
            "education" to education,
            "experience" to experience,
            "skills" to skills,
            "previousWorkplace" to previousWorkplace
        )



        val email = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.email
        val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()

        if (email != null) {
            db.collection("employees")
                .document(email)
                .set(employee)
                .addOnSuccessListener {
                    android.widget.Toast.makeText(this, "User data saved successfully", android.widget.Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    android.widget.Toast.makeText(this, "Failed to save user data", android.widget.Toast.LENGTH_SHORT).show()
                }
        }
    }
}