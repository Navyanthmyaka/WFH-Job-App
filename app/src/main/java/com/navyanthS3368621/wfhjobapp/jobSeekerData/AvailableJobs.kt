package com.navyanthS3368621.wfhjobapp.jobSeekerData


data class AvailableJobs(
    val jobName: String = "",
    val companyName: String = "",
    val place: String = "",
    val lastDate: String = "",
    val applicationStatus: String = "",
    val category: String = "",
    val salary: String = "",
    val requiredExperience: String = "",
    val employmentType: String = "", // Full-time, Part-time, Freelancer

)