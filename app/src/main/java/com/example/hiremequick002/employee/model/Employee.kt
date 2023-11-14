package com.example.hiremequick002.employee.model

data class Employee(
    val userId: String,
    val userEmail: String,
    val userRole: String = "employee",
    val userName: String,
    val userFirstName: String,
    val userLastName: String,
    val skills: List<String>
)
