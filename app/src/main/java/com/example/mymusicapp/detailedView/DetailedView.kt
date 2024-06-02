package com.example.mymusicapp.detailedView

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mymusicapp.R
import com.example.mymusicapp.data.MusicRepository

@Composable
fun DetailedView(
    musicId: String,
    navController: NavHostController
) {
    val context = LocalContext.current

    val viewModel: DetailedViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailedViewModel::class.java)) {
                    return DetailedViewModel(musicId, MusicRepository()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )

    val music by viewModel.musicLiveData.observeAsState()



    if (music != null)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.teal_700))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Title(title = music!!.title)
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = {
                        viewModel.deleteMusic(music!!, navController)
                        Toast.makeText(context, "Music deleted", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.teal_200),
                        contentColor = Color.White
                    )
                ) {
                    Text("Delete", color = Color.White)
                }
            }
            Description(description = music!!.description)
            MyDivider()
            Spacer(Modifier.height(16.dp))
            Text("Artists: ")
            Artists(artists = music!!.artists)
            Genre(genre = music!!.genre)
            Duration(duration = music!!.duration)

        }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 30.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        color = Color.Black,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Artists(artists: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var i = 0
        for (artist in artists) {
            ArtistsTextView(artist = artist, ++i == artists.size)
        }
    }
}

@Composable
fun Genre(genre: String) {
    Text(
        text = "Genre: $genre",
        color = Color.Black,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Duration(duration: Int) {
    Text(
        text = "Duration: $duration",
        color = Color.Black,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ArtistsTextView(artist: String, isTheLastOne: Boolean) {
    Text(
        modifier = Modifier.padding(6.dp, 3.dp),
        text = if (isTheLastOne) artist else "$artist,",
        color = Color.DarkGray,
        fontSize = 28.sp,
        fontFamily = FontFamily.Cursive,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun MyDivider() {
    Divider(
        modifier = Modifier.padding(0.dp, 10.dp),
        color = Color.LightGray
    )
}
