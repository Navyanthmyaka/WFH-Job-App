package com.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class JobSeekerHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobSeekerHomeActivityScreen(::onItemSelected)
        }
    }

    private fun onItemSelected(itemName: String) {
        if (itemName == "Jobs") {
            val intent = Intent(this, JobsActivity::class.java)
            startActivity(intent)
        } else if (itemName == "InterView Tips") {
            val intent = Intent(this, InterviewTipsActivity::class.java)
            startActivity(intent)
        } else if (itemName == "Resume Templates") {
            val intent = Intent(this, TemplatesActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}

@Composable
fun JobSeekerHomeActivityScreen(onItemSelected: (itemName: String) -> Unit) {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Button(
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
                text = "JobSeeker Home ",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
        }*/

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

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Available Jobs",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .width(36.dp)
                        .height(36.dp)
                        .clickable {
                            context.startActivity(Intent(context,JobSeekerProfileActivity::class.java))
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Full Time Job/Work",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        )

        Text(
            text = "Get Job from Home",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp)) // Curved edges
                .border(
                    width = 2.dp,
                    color = Color.Gray, // Border color
                    shape = RoundedCornerShape(12.dp) // Match the corner radius
                )
                .padding(top = 16.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Column(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        onItemSelected("Jobs")
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.job),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Available\nJobs",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        onItemSelected("Resume Templates")
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cv),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))


                Text(
                    text = "Resume\nTemplates",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        onItemSelected("InterView Tips")
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.interview),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "InterView\nTips",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

//        Spacer(modifier = Modifier.height(12.dp))
//        JSHome("Jobs", R.drawable.job, onItemSelected)
//        Spacer(modifier = Modifier.height(8.dp))
//        JSHome("Resume Templates", R.drawable.cv, onItemSelected)
//        Spacer(modifier = Modifier.height(8.dp))
//        JSHome("InterView Tips", R.drawable.interview, onItemSelected)

        Spacer(modifier = Modifier.weight(1f))
        JSHome("Logout", R.drawable.baseline_logout_36, onItemSelected)


    }
}


@Composable
fun JSHome(title: String, image: Int, onItemSelected: (itemName: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clickable { onItemSelected(title) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )

        {
            // Image before the text
            Image(
                modifier = Modifier.size(36.dp),
                painter = painterResource(id = image),
                contentDescription = "Item Image"
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",

                )
        }
    }
}

