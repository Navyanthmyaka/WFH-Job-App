package com.navyanthS3368621.wfhjobapp.jobSeekerData

import android.content.Context

object JobSeekerLocalData {

    fun saveJobSeekerStatus(context: Context, value: Boolean) {
        val seekerStatus = context.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        val statusEditor = seekerStatus.edit()
        statusEditor.putBoolean("JOBSEEKER_LS", value).apply()
    }

    fun getJobSeekerStatus(context: Context): Boolean {
        val seekerStatus = context.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        return seekerStatus.getBoolean("JOBSEEKER_LS", false)
    }

    fun saveJobSeekerEmail(context: Context, email: String) {
        val seekerEmail = context.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        val emailEditor = seekerEmail.edit()
        emailEditor.putString("JOBSEEKER_EMAIL", email).apply()
    }

    fun getJobSeekerEmail(context: Context): String? {
        val seekerEmail = context.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        return seekerEmail.getString("JOBSEEKER_EMAIL", null)
    }
}