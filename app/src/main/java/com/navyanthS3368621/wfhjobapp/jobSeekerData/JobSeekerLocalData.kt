package com.navyanthS3368621.wfhjobapp.jobSeekerData

import android.content.Context

object JobSeekerLocalData {
    private const val PREFS_NAME = "WFHJobs"

    fun saveJobSeekerStatus(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("JOBSEEKER_LS", value).apply()
    }

    fun getJobSeekerStatus(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("JOBSEEKER_LS", false)
    }

    fun saveJobSeekerEmail(context: Context, email: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("JOBSEEKER_EMAIL", email).apply()
    }

    fun getJobSeekerEmail(context: Context): String? {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString("JOBSEEKER_EMAIL", null)
    }
}