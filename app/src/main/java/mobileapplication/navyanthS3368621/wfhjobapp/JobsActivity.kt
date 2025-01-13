package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.firebase.database.FirebaseDatabase
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.AvailableJobs
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.JobSeekerLocalData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class JobsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JobsList()
        }
    }
}

@Composable
fun JobsList() {
    val baseContext = LocalContext.current as Activity
    val availableJobs = getAvailableJobs()
    val categories = availableJobs.map { it.category }.distinct()
    var selectedCategory by remember { mutableStateOf("All") }
    val filteredJobs =
        if (selectedCategory == "All") availableJobs else availableJobs.filter { it.category == selectedCategory }

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
                            baseContext.finish()
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

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {


            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Chip(
                        isSelected = selectedCategory == "All",
                        text = "All",
                        onClick = { selectedCategory = "All" }
                    )
                }
                items(categories.size) { index ->
                    Chip(
                        isSelected = selectedCategory == categories[index],
                        text = categories[index],
                        onClick = { selectedCategory = categories[index] }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            items(filteredJobs.size) { index ->
                JobCard(filteredJobs[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun Chip(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
        contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun JobCard(job: AvailableJobs) {

    var applyJobD by remember { mutableStateOf(false) }

    val baseContext = LocalContext.current

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

                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Apply",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(6.dp)
                        .clickable {
                            applyJobD = true
                        }
                )

            }

            if (applyJobD) {
                ApplyJob(job)
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
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Open",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Blue,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(Color.Blue)
                        .padding(6.dp)
                        .clickable {
                            JobSelected.placeName =job.place
                            (baseContext as Activity).startActivity(Intent(baseContext,
                                SeeJobLocation::class.java))
                        }
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


fun getAvailableJobs(): List<AvailableJobs> {
    return listOf(
        AvailableJobs(
            jobName = "Web Application Developer",
            companyName = "Open CRM",
            place = "Richmond",
            lastDate = "2024-12-15",
            applicationStatus = "Open",
            category = "Software Development",
            salary = "£120,000/year",
            requiredExperience = "A good knowledge and practical experience of PHP, MySQL, HTML, and CSS.",
            employmentType = "Full-time"
        ),
        AvailableJobs(
            jobName = "PHP Web Developer",
            companyName = "VIA Creative",
            place = "Middlesbrough TS1",
            lastDate = "2024-12-20",
            applicationStatus = "Open",
            category = "Software Development",
            salary = "£80,000/year",
            requiredExperience = "You will be responsible for helping deliver all aspects of webdevelopment",
            employmentType = "Full-time"
        ),
        AvailableJobs(
            jobName = "Backend Development Expert",
            companyName = "Outlier",
            place = "Teesside University",
            lastDate = "2024-12-10",
            applicationStatus = "Closed",
            category = "Software Development",
            salary = "£30 an hour",
            requiredExperience = "5+ years",
            employmentType = "Freelance"
        ),
        AvailableJobs(
            jobName = "Customer Service Consultant",
            companyName = "INTERVIEWING NOW",
            place = "Sunderland",
            lastDate = "2024-12-10",
            applicationStatus = "INTERVIEWING NOW",
            category = "Customer support ",
            salary = "£23,200 plus cash allowance of £2088 plus excellent benefits including private healthcare and bonus",
            requiredExperience = "Transactional Fraud Operations",
            employmentType = "Full-time"
        )
    )
}


@Composable
fun ApplyJob(job: AvailableJobs) {
    var showDialog by remember { mutableStateOf(true) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }

    val baseContext = LocalContext.current

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = job.jobName,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Blue
                    )

                    Text(
                        text = "Application",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Blue
                    )

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text("Address") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false
                    )

                    Button(
                        onClick = {
                            showDialog = false

                            if (name.text.isEmpty() || email.text.isEmpty() || address.text.isEmpty()) {
                                Toast.makeText(
                                    baseContext,
                                    "Please enter all details",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                job.applicantName = name.text
                                job.applicantEmail = email.text
                                job.applicantAddress = address.text
                                job.appliedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                                sendApplication(job, baseContext)
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}


private fun sendApplication(application: AvailableJobs, baseContext: Context) {

    val studentEmail = JobSeekerLocalData.getJobSeekerEmail(baseContext)!!.replace(".", ",")
    FirebaseDatabase.getInstance().getReference("Applications").child(studentEmail)
        .child(SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date()))
        .setValue(application)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(baseContext, "Job Applied Successfully", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(
                    baseContext,
                    "Failed to apply job! Try again",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener {
            Toast.makeText(
                baseContext,
                "Failed to apply job! Try again",
                Toast.LENGTH_SHORT
            ).show()
        }

}