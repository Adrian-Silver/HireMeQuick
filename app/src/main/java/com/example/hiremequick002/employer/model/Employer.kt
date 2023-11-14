package com.example.hiremequick002.employer.model

data class Employer(
    val userId: String,
    val userEmail: String,
    val userRole: String = "employer",
    val userName: String,
    val userFirstName: String,
    val userLastName: String,
    val userCompanyName: String
)
