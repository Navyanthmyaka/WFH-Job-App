package mobileapplication.navyanthS3368621.wfhjobapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeeJobLocation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapScreenWithPlaceName(this)
        }
    }
}

object JobSelected {
    var placeName: String = ""
}

@Composable
fun MapScreenWithPlaceName(context: Context) {
    val cameraPositionState = rememberCameraPositionState()

    var latLng by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(Unit) {
        latLng = getCoordinatesFromPlaceName(JobSelected.placeName, context)
        latLng?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 12f)
        }
    }

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
                            (context as Activity).finish()
                        }
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Job Location",
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            latLng?.let {
                Marker(
                    state = rememberMarkerState(position = it),
                    title = "Sunderland",
                    snippet = "This is Sunderland"
                )
            }
        }

    }
}

suspend fun getCoordinatesFromPlaceName(placeName: String, context: Context): LatLng? {
    return withContext(Dispatchers.IO) {
        try {
            val geocoder = android.location.Geocoder(context)
            val addresses = geocoder.getFromLocationName(placeName, 1)
            if (addresses!!.isNotEmpty()) {
                val location = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}