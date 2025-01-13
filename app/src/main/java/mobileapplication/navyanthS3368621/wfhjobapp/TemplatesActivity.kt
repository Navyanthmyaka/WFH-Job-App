package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.SelectTemplate

class TemplatesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TemplateScreen(
                onTemplateViewClicked = { templatedId ->
                    onTemplateClicked(templatedId)
                }
            )
        }
    }

    private fun onTemplateClicked(templatedId: String) {
        when{
            templatedId == "Template 1" -> {

                SelectTemplate.templateUrl="https://resumegenius.com/wp-content/uploads/Chicago-Resume-Template-Dark-Blue-Hub.png"

                startActivity(Intent(this, ResumeTemplateActivity::class.java))
            }
            templatedId == "Template 2" -> {

                SelectTemplate.templateUrl="https://www.my-resume-templates.com/wp-content/uploads/2023/07/job-resume-template-259.jpg"

                startActivity(Intent(this, ResumeTemplateActivity::class.java))
            }
            templatedId == "Template 3" -> {

                SelectTemplate.templateUrl="https://d.novoresume.com/images/doc/creative-resume-template.png"

                startActivity(Intent(this, ResumeTemplateActivity::class.java))
            }
            templatedId == "Template 4" -> {
                SelectTemplate.templateUrl="https://d25zcttzf44i59.cloudfront.net/google-analytics-google-docs-resume-template.png"

                startActivity(Intent(this, ResumeTemplateActivity::class.java))

            }
        }

    }
}

@Composable
fun TemplateScreen(
    onTemplateViewClicked: (templatedId: String) -> Unit
) {
    val baseContext = LocalContext.current as Activity
    val specialities = listOf(
        "Template 1",
        "Template 2",
        "Template 3",
        "Template 4",
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                    text = "Resume Templates",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier

        ) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(specialities.size) { index ->
                    JobCard(specialities[index], onTemplateViewClicked)
                }
            }
        }

    }

}

@Composable
fun JobCard(title: String, onTemplateViewClicked: (templatedId: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTemplateViewClicked(title) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward"
            )
        }
    }

}