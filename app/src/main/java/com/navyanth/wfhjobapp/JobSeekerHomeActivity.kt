package com.navyanth.wfhjobapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        } else {
            val intent = Intent(this, TemplatesActivity::class.java)
            startActivity(intent)
        }

    }
}

@Composable
fun JobSeekerHomeActivityScreen(onItemSelected: (itemName: String) -> Unit) {
    val context = LocalContext.current as Activity

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
                text = "JobSeeker Home ",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        JSHome("Jobs", R.drawable.job, onItemSelected)
        Spacer(modifier = Modifier.height(8.dp))
        JSHome("Resume Templates", R.drawable.cv, onItemSelected)


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

