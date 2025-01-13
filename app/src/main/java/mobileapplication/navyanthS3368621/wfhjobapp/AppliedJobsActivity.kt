package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.AvailableJobs
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.JobSeekerLocalData

class AppliedJobsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppliedJob()
        }
    }
}

@Composable
fun AppliedJob() {
    val baseContext = LocalContext.current

    val applicantEmail = JobSeekerLocalData.getJobSeekerEmail(baseContext)!!

    var appliedJobsList by remember { mutableStateOf(listOf<AvailableJobs>()) }
    var isDataFetched by remember { mutableStateOf(true) }

    fun fetchAppliedJobs() {
        isDataFetched = true
        loadAppliedJobs(applicantEmail) { application ->
            appliedJobsList = application
            isDataFetched = false
        }
    }

    LaunchedEffect(applicantEmail) {
        fetchAppliedJobs()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        Surface(
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            ),
            color = colorResource(id = R.color.CoralRose)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                        .clickable {
                            (baseContext as Activity).finish()
                        }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Available Jobs",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            items(appliedJobsList.size) { index ->
                AppliedJobCard(appliedJobsList[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }


}


@Composable
fun AppliedJobCard(job: AvailableJobs) {


    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Row {

                Column {

                    Text(
                        text = job.jobName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = job.companyName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Text(
                        text = "Applied Date : ${job.appliedDate}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier

                    )
                }


            }


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_location_on_24), // Replace with your location icon
                    contentDescription = "Map Location",
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.place,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.salary_ic),
                    contentDescription = "Salary Icon",
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.salary,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.experience_ic),
                    contentDescription = "Experience Icon",
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.requiredExperience,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fulltime),
                    contentDescription = "Time Icon",
                    modifier = Modifier
                        .size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = job.employmentType,
                    fontSize = 14.sp,
                    color = Color.Black
                )

            }


        }
    }
}


fun loadAppliedJobs(studentMail: String, onAppliedJobLoaded: (List<AvailableJobs>) -> Unit) {

    val path = "Applications/${studentMail.replace(".", ",")}"
    FirebaseDatabase.getInstance().getReference(path)
        .addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val application = mutableListOf<AvailableJobs>()
                for (donationSnapshot in snapshot.children) {
                    val applicationItem = donationSnapshot.getValue(AvailableJobs::class.java)
                    applicationItem?.let { application.add(it) }
                }
                onAppliedJobLoaded(application)
            }

            override fun onCancelled(error: DatabaseError) {
                onAppliedJobLoaded(emptyList())
            }
        })
}