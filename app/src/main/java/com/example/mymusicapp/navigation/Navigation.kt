package com.example.mymusicapp.navigation


import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mymusicapp.navigation.Screens
import com.example.mymusicapp.addNew.AddNewActivity
import com.example.mymusicapp.detailedView.DetailedView
import com.example.mymusicapp.list.MusicList

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screens.MusicListScreen.route) {
        composable(Screens.MusicListScreen.route) {
            MusicList(onAddNewMusicClick = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
            }, onMusicClick = { musicId ->
                navController.navigate("detailedView/$musicId")
            }
            )
        }

        composable(
            route = "detailedView/{MusicId}"
        ) { backStackEntry ->
            DetailedView(musicId = backStackEntry.arguments?.getString("MusicId")!!,
            navController = navController)
        }
    }
}