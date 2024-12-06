package com.navyanth.wfhjobapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.navyanth.wfhjobapp.jobSeekerData.AvailableJobs

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

    val availableJobs = getAvailableJobs()

    Column(modifier = Modifier.fillMaxSize()) {

        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(vertical = 12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.CoralRose),
                contentColor = colorResource(
                    id = R.color.white
                )
            ),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            )
        ) {
            Text(
                text = "Available Jobs",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {

            items(availableJobs.size) { index ->
                JobCard(availableJobs[index])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun JobCard(
    jobItem: AvailableJobs
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = jobItem.jobName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))


                Text(
                    text = jobItem.companyName,
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Location Row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_location_on_24), // Replace with your location icon
                        contentDescription = "Location Icon",
                        modifier = Modifier
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = jobItem.place,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Last Date to Apply
                Text(
                    text = "Last Date: ${jobItem.lastDate}",
                    fontSize = 14.sp,
                    color = Color.Red
                )
            }

            // Apply Button
            Button(
                onClick = { /* Handle Apply Action */ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Apply")
            }
        }
    }
}

fun getAvailableJobs(): List<AvailableJobs> {
    return listOf(
        AvailableJobs(
            jobName = "Software Engineer",
            companyName = "Tech Solutions",
            place = "New York, NY",
            lastDate = "2024-12-15",
            applicationStatus = "Open"
        ),
        AvailableJobs(
            jobName = "Data Analyst",
            companyName = "Data Insights Co.",
            place = "Los Angeles, CA",
            lastDate = "2024-12-20",
            applicationStatus = "Open"
        ),
        AvailableJobs(
            jobName = "Product Manager",
            companyName = "Innovatech Ltd.",
            place = "Chicago, IL",
            lastDate = "2024-12-10",
            applicationStatus = "Closed"
        ),
        AvailableJobs(
            jobName = "UI/UX Designer",
            companyName = "Creative Minds",
            place = "Austin, TX",
            lastDate = "2024-12-22",
            applicationStatus = "Open"
        ),
        AvailableJobs(
            jobName = "System Administrator",
            companyName = "SecureNet Corp.",
            place = "San Francisco, CA",
            lastDate = "2024-12-18",
            applicationStatus = "Open"
        ),
        AvailableJobs(
            jobName = "Marketing Specialist",
            companyName = "BrandMasters Inc.",
            place = "Miami, FL",
            lastDate = "2024-12-05",
            applicationStatus = "Closed"
        ),
        AvailableJobs(
            jobName = "Business Analyst",
            companyName = "Global Solutions",
            place = "Seattle, WA",
            lastDate = "2024-12-25",
            applicationStatus = "Open"
        ),
        AvailableJobs(
            jobName = "DevOps Engineer",
            companyName = "CloudWorks",
            place = "Denver, CO",
            lastDate = "2024-12-08",
            applicationStatus = "Closed"
        ),
        AvailableJobs(
            jobName = "Content Writer",
            companyName = "WordCraft Ltd.",
            place = "Boston, MA",
            lastDate = "2024-12-12",
            applicationStatus = "Open"
        ),
        AvailableJobs(
            jobName = "AI Researcher",
            companyName = "NextGen AI",
            place = "Palo Alto, CA",
            lastDate = "2024-12-30",
            applicationStatus = "Open"
        )
    )
}
