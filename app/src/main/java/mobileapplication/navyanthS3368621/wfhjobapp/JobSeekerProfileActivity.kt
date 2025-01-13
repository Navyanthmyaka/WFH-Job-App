package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.JobSeekerLocalData

class JobSeekerProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobSeekerProfileActivityScreen()
        }
    }
}

@Composable
fun JobSeekerProfileActivityScreen() {
    val baseContext = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
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
                    text = "Profile",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            }
        }


        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Image(
                painter = painterResource(id = R.drawable.user_profile),
                contentDescription = "User Profile",
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
                    .clickable {
                        baseContext.finish()
                    }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = JobSeekerLocalData.getJobSeekerEmail(baseContext)!!,
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold
            )

        }

    }
}