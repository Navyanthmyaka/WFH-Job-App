package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase
import mobileapplication.navyanthS3368621.wfhjobapp.jobSeekerData.JobSeekerLocalData

class UserLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserLoginActivityScreen()
        }
    }
}

@Composable
fun UserLoginActivityScreen() {
    var jsId by remember { mutableStateOf("") }
    var jsPs by remember { mutableStateOf("") }

    val baseContext = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.SoftBlue)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = "Work From Home Opportunities",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Text(
            text = "by Navyanth",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))


        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clip(RoundedCornerShape(26.dp)),

            ) {

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsId,
                onValueChange = { jsId = it },
                label = { Text("Enter Email") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Spacer(
                            modifier = Modifier
                                .width(3.dp)
                                .height(24.dp)
                                .background(Color.Gray)
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsPs,
                onValueChange = { jsPs = it },
                label = { Text("Enter Password") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Email Password"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Spacer(
                            modifier = Modifier
                                .width(3.dp)
                                .height(24.dp)
                                .background(Color.Gray)
                        )
                    }
                },
            )

            Spacer(modifier = Modifier.height(46.dp))

            Button(
                onClick = {

                    if (jsId.isEmpty()) {
                        Toast.makeText(baseContext, "Email is Empty", Toast.LENGTH_SHORT).show()
                    } else if (jsPs.isEmpty()) {
                        Toast.makeText(baseContext, "Password is Empty", Toast.LENGTH_SHORT).show()
                    } else {

                        val userEmail = jsId.trim()
                        val userPassword = jsPs.trim()

                        val database = FirebaseDatabase.getInstance()
                        val jobSeekersReference = database.reference


                        jobSeekersReference.child("JobSeekers").child(userEmail.replace(".", ","))
                            .get()
                            .addOnSuccessListener { snapshot ->
                                if (snapshot.exists()) {
                                    val jobSeekerData =
                                        snapshot.getValue(JobSeekerData::class.java)
                                    jobSeekerData?.let {
                                        if (userPassword == it.password) {
                                            Toast.makeText(
                                                baseContext,
                                                "Login Successfull",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            JobSeekerLocalData.saveJobSeekerStatus(
                                                baseContext,
                                                true
                                            )
                                            JobSeekerLocalData.saveJobSeekerEmail(
                                                baseContext,
                                                userEmail
                                            )
                                            baseContext.startActivity(
                                                Intent(
                                                    baseContext,
                                                    JobSeekerHomeActivity::class.java
                                                )
                                            )
                                            baseContext.finish()
                                        } else {
                                            Toast.makeText(
                                                baseContext,
                                                "Incorrect Credentials",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Your data is not with us!",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }.addOnFailureListener { exception ->
                                println("Seems something is wrong")
                            }
                    }
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
                    text = "Login",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                )
            }

        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "I'm new to this app !", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Join Now",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.clickable {
                    baseContext.startActivity(Intent(baseContext, CreateAccountActivity::class.java))
                    baseContext.finish()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}


@Preview(showBackground = true)
@Composable
fun UserLoginActivityScreenPreview() {
    UserLoginActivityScreen()
}


data class JobSeekerData(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val graduation: String = ""
)