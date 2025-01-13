package mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData

import android.content.Context

object JobSeekerLocalData {

    fun saveJobSeekerStatus(baseContext: Context, value: Boolean) {
        val seekerLS = baseContext.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        val prefStatus = seekerLS.edit()
        prefStatus.putBoolean("JOBSEEKER_LS", value).apply()
    }

    fun getJobSeekerStatus(baseContext: Context): Boolean {
        val seekerLS = baseContext.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        return seekerLS.getBoolean("JOBSEEKER_LS", false)
    }

    fun saveJobSeekerEmail(baseContext: Context, email: String) {
        val seekerEM = baseContext.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        val prefStatus = seekerEM.edit()
        prefStatus.putString("JOBSEEKER_EMAIL", email).apply()
    }

    fun getJobSeekerEmail(baseContext: Context): String? {
        val seekerEM = baseContext.getSharedPreferences("WFHJobs", Context.MODE_PRIVATE)
        return seekerEM.getString("JOBSEEKER_EMAIL", null)
    }
}