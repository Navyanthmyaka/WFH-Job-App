package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.FirebaseDatabase

class CreateAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CreateAccountActivityScreen()
        }
    }
}

@Composable
fun CreateAccountActivityScreen() {

    var jsFN by remember { mutableStateOf("") }
    var jsGRD by remember { mutableStateOf("") }
    var jsEM by remember { mutableStateOf("") }
    var jsPS by remember { mutableStateOf("") }
    var jsCPS by remember { mutableStateOf("") }

    val baseContext = LocalContext.current as Activity

    var selectedGraduation by remember { mutableStateOf("") }


    val graduationOptions = listOf(
        "Master in Computer Science",
        "B Tech in Computer Science",
        "Master in Data Science",
        "Master in Machine Learning",
        "Master in Artificial Intelligence",
        "Bachelor in Information Technology"
    )


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
                value = jsFN,
                onValueChange = { jsFN = it },
                label = { Text("Enter FullName") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                ),
                shape = RoundedCornerShape(32.dp),
                leadingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            imageVector = Icons.Default.AccountCircle,
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

            GraduationList(
                options = graduationOptions,
                selectedOption = jsGRD,
                onOptionSelected = { jsGRD = it }
            )

            Spacer(modifier = Modifier.height(0.dp))

//            OutlinedTextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 12.dp),
//                value = jsGRD,
//                onValueChange = { jsGRD = it },
//                label = { Text("Enter Graduation") },
//                colors = TextFieldDefaults.colors(
//                    unfocusedContainerColor = Color.White,
//                    focusedContainerColor = Color.White,
//                ),
//                shape = RoundedCornerShape(32.dp),
//                leadingIcon = {
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Spacer(modifier = Modifier.width(6.dp))
//
//                        Image(
//                            modifier = Modifier
//                                .width(24.dp)
//                                .height(24.dp),
//                            painter = painterResource(id = R.drawable.ic_graduate),
//                            contentDescription = "Travel Guide",
//                        )
//                        Spacer(modifier = Modifier.width(6.dp))
//                        Spacer(
//                            modifier = Modifier
//                                .width(3.dp)
//                                .height(24.dp)
//                                .background(Color.Gray)
//                        )
//                    }
//                },
//            )

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsEM,
                onValueChange = { jsEM = it },
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

            Spacer(modifier = Modifier.height(0.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsPS,
                onValueChange = { jsPS = it },
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

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = jsCPS,
                onValueChange = { jsCPS = it },
                label = { Text("Confirm Password") },
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
                            contentDescription = "Confirm Password"
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

                    if (jsFN.isEmpty()) {
                        Toast.makeText(baseContext, "Full Name is Empty", Toast.LENGTH_SHORT).show()
                    } else if (jsGRD.isEmpty()) {
                        Toast.makeText(baseContext, "Graduation is Empty", Toast.LENGTH_SHORT).show()
                    } else if (jsEM.isEmpty()) {
                        Toast.makeText(baseContext, "Email is Empty", Toast.LENGTH_SHORT).show()
                    } else if (jsPS.isEmpty()) {
                        Toast.makeText(baseContext, "Password is Empty", Toast.LENGTH_SHORT).show()
                    }else if(jsPS != jsCPS)
                    {
                        Toast.makeText(baseContext, "Password Mismatch", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val jobSeekerData = JobSeekerData(
                            name = jsFN,
                            email = jsEM,
                            password = jsPS,
                            graduation = jsGRD
                        )

                        val firebaseDatabase = FirebaseDatabase.getInstance()
                        val databaseReference = firebaseDatabase.getReference("JobSeekers")

                        databaseReference.child(jobSeekerData.email.replace(".", ","))
                            .setValue(jobSeekerData)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    Toast.makeText(
                                        baseContext,
                                        "Registration Successful",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    baseContext.startActivity(
                                        Intent(
                                            baseContext,
                                            UserLoginActivity::class.java
                                        )
                                    )
                                    baseContext.finish()
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Failed to open account for you",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(
                                    baseContext,
                                    "Failed to open account for you",
                                    Toast.LENGTH_SHORT
                                ).show()
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
                    text = "Register",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                )
            }

        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "I'm an old user !", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Login Now",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.clickable {
                    baseContext.startActivity(Intent(baseContext, UserLoginActivity::class.java))
                    baseContext.finish()
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}



@Preview(showBackground = true)
@Composable
fun CreateAccountActivityScreenPreview() {
    CreateAccountActivityScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraduationList(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    label: String = "Select your graduation"
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        TextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)

        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}