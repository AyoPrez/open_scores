package com.ayoprez.openscores

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ayoprez.openscores.ui.theme.OpenScoresTheme

@Composable
fun SportSelectionScreen(navController: NavController) {
    App {
        ChooseSportScreen(navController = navController)
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    OpenScoresTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun ChooseSportScreen(sportNames: List<String> = listOf(stringResource(R.string.football), stringResource(R.string.basketball)), navController: NavController) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        ExplanationText()
        Spacer(modifier = Modifier.height(20.dp))

        for (buttonText: String in sportNames) {
            SelectionButton(text = buttonText, navController)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun ExplanationText() {
    Text(
        stringResource(R.string.sport_selection_explanation),
        Modifier.padding(20.dp)
    )
}

@Composable
fun SelectionButton(text: String, navController: NavController) {
    OutlinedButton(
        onClick = {
            navController.navigate(Screen.GameScoresScreen.passValue(text))
        },
        modifier = Modifier.semantics { contentDescription = text }
    ) {
        Text(
            text,
            modifier = Modifier.padding(20.dp),
            style = TextStyle(fontSize = 20.sp)
        )
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
fun SportSelectionPreview() {
    SportSelectionScreen(navController = rememberNavController())
}