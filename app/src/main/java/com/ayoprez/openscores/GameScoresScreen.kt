package com.ayoprez.openscores

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ayoprez.openscores.model.GameEntry
import com.ayoprez.openscores.model.GameListViewModel

@Composable
fun GameScoresScreen(navController: NavController, sportName: String?) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TopBar(navController = navController, sportName = sportName)
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(
                hint = stringResource(R.string.search_team),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .padding(10.dp, 20.dp)
            )
//            GameEntryRow(entry = , navController = navController)
        }
    }
}

@Composable
fun TopBar(navController: NavController, sportName: String?) {
    Row {
        OutlinedButton(onClick = {
            navController.popBackStack()
        }) {
            Text(text = stringResource(id = R.string.back))
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = sportName ?: "",
            style = TextStyle(fontSize = 20.sp)
        )
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {

    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(border = BorderStroke(1.dp, Color.Black))
                .padding(20.dp, 10.dp)
                .onFocusChanged {
                    isHintDisplayed != it.isFocused
                }
        )

        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(20.dp, 10.dp)
            )
        }
    }

    Divider()
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
                navController.navigate(Screen.GameDetailsScreen.passValue(entry.id))
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
    GameScoresScreen(navController = rememberNavController(), "Sport")
}