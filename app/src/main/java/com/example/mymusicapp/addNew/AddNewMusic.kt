package com.example.mymusicapp.addNew

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymusicapp.MainActivity
import com.example.mymusicapp.R
import com.example.mymusicapp.data.MusicRepository
import com.example.mymusicapp.models.Music


@Composable
fun AddNewMusic(
    viewModel: AddNewMusicViewModel = AddNewMusicViewModel(MusicRepository())
) {
    val localContext = LocalContext.current

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val genre = remember{ mutableStateOf("") }
    val artists = remember { mutableStateOf("") }
    val duration = remember { mutableStateOf("") }

    val response by viewModel.insertResponseLiveData.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            CreateNewMusicPageTitle()
            Spacer(modifier = Modifier.height(15.dp))
            TitleInput(title = title.value, onTitleChange = { title.value = it })
            Spacer(Modifier.height(16.dp))
            DescriptionInput(description = description.value,
                onDescriptionChange = { description.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            Genre(genre = genre.value, onGenreChange = { genre.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            ArtistsInput(artists = artists.value, onArtistsChange = { artists.value = it })
            Spacer(modifier = Modifier.height(15.dp))
            Duration(duration = duration.value, onDurationChanged = { duration.value = it })
            Spacer(modifier = Modifier.height(30.dp))

            AddNewButton {
                val constructedMusic: Music? = constructMusicIfInputValid(
                    titleInput = title.value,
                    descriptionInput = description.value,
                    genreInput = genre.value,
                    artistsInput = artists.value,
                    durationInput = duration.value,

                    context = localContext
                )


                if (constructedMusic != null
                ) {
                    viewModel.saveNewMusic(
                        constructedMusic
                    )
                }
            }
        }

        if (response != null) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 22.sp,
                text = if (response!!.status == "OK") stringResource(id = R.string.saved_success_msg)
                else stringResource(id = R.string.saved_fail_msg)
            )

            if (response!!.status == "OK") {
                localContext.startActivity(Intent(localContext, MainActivity::class.java))
            }
        }
    }

}

@Composable
private fun CreateNewMusicPageTitle() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.title_activity_add_new_Music),
        color = Color.White,
        fontSize = 26.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleInput(title: String, onTitleChange: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.white_900)
        ),
        value = title,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onTitleChange(it) },
        label = {
            Text(stringResource(id = R.string.Music_title_input_hint), color = Color.Black)
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.white_900)
        ),
        value = description,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onDescriptionChange(it) },
        label = {
            Text(stringResource(id = R.string.Music_desc_input_hint), color = Color.Black)
        })
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ArtistsInput(artists: String, onArtistsChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white_900)
        ),
        value = artists,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onArtistsChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_artists_input_hint), color = Color.Black)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Genre(genre: String, onGenreChange: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.white_900)
        ),
        value = genre,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onGenreChange(it) },
        label = {
            Text(stringResource(id = R.string.Music_genre_input_hint), color = Color.Black)
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Duration(duration: String, onDurationChanged: (String) -> Unit) {
    TextField(modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white_900)
        ),
        value = duration,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onDurationChanged(it) },
        label = {
            Text(stringResource(id = R.string.music_duration_input_hint), color = Color.Black)
        })
}


@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_700), colorResource(id = R.color.white)
        )

    ) {
        Text(
            fontSize = 22.sp, text = stringResource(id = R.string.save_btn_text)
        )
    }
}

private fun constructMusicIfInputValid(
    titleInput: String?,
    descriptionInput: String?,
    genreInput: String,
    artistsInput: String?,
    durationInput: String?,
    context: Context
): Music? {
    if (titleInput.isNullOrEmpty() ||
        descriptionInput.isNullOrEmpty() ||
        genreInput.isEmpty() ||
        artistsInput.isNullOrEmpty() ||
        durationInput.isNullOrEmpty()
    ) {
        Toast.makeText(
            context, context.resources.getString(R.string.Music_all_fields_compulsory_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }


    return Music(
        title = titleInput,
        description = descriptionInput,
        artists = artistsInput.split(","),
        genre = genreInput,
        duration = durationInput.toInt()
    )
}