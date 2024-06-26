package com.example.mymusicapp.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymusicapp.R
import com.example.mymusicapp.data.MusicRepository
import com.example.mymusicapp.models.Music

@Composable
fun MusicList(
    viewModel: MusicListViewModel = MusicListViewModel(MusicRepository()),
    onAddNewMusicClick: () -> Unit,
    onMusicClick: (String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        val music by viewModel.musicLiveData.observeAsState()

        if (!music.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(items = music!!, itemContent = { item ->
                    MusicItem(music = item, onMusicClick)
                })
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.white),
                onClick = onAddNewMusicClick
            ) {
                Text(
                    stringResource(id = R.string.add_new_button),
                    modifier = Modifier.padding(25.dp, 10.dp),
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.teal_200),
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Composable
private fun MusicItem(music: Music, onMusicClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.teal_200), //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onMusicClick(music.id) }
        ) {
            MusicItemName(name = music.title)
            if (music.description.isNotEmpty())
                MusicItemDesc(desc = music.description)
        }
    }
}


@Composable
private fun MusicItemName(name: String) {
    Text(
        text = name,
        fontSize = 23.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun MusicItemDesc(desc: String) {
    Text(
        text = desc,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.White,
        fontSize = 25.sp,
        fontFamily = FontFamily.Cursive,
        textAlign = TextAlign.Left
    )
}