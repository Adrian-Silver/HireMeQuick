package com.example.hiremequick002.employer.model

data class Employer(
    val userId: String,
    val userEmail: String,
    val userRole: String = "employer",
    val userName: String,
    val userFirstName: String,
    val userLastName: String,
    val userCompanyName: String

//    val employerId: String,
//    val employerEmail: String,
//    val employerRole: String,
//    val employerName: String,
//    val employerFirstName: String,
//    val employerLastName: String,
//    val employerCompanyName: String,
)
