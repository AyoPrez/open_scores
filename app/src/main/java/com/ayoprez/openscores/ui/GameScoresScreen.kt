package com.ayoprez.openscores.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ayoprez.openscores.R
import com.ayoprez.openscores.domain.models.GameEntry
import com.ayoprez.openscores.domain.viewmodels.GameListViewModel

@Composable
fun GameScoresScreen(navController: NavController,
                     sportId: String,
                     viewModel: GameListViewModel = hiltViewModel()) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        viewModel.getSportNameById(sportId)

        Column {
            TopBar(navController = navController, sportName = viewModel.sportName.value)
            Spacer(modifier = Modifier.height(20.dp))
//            GameEntryRow(entry = , navController = navController)
        }
    }
}

@Composable
fun TopBar(navController: NavController, sportName: String?) {
    TopAppBar(
        {
            Text(
                text = sportName ?: "",
                style = TextStyle(fontSize = 20.sp)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.navigate_up),
//                    tint = MaterialTheme.colors.primary
                )
            }
        },
    )
}

@Composable
fun GameEntryRow(
    entry: GameEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: GameListViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(ScreenNavigation.GameDetailsScreen.passValue(entry.id))
            }
    ) {
        Text(entry.localTeamName)
        Text(entry.localTeamScore.toString())
        Text(entry.visitTeamScore.toString())
        Text(entry.visitTeamName)
    }

}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun GameScoresPreview() {
    GameScoresScreen(navController = rememberNavController(), "10")
}