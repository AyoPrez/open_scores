package com.ayoprez.openscores.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ayoprez.openscores.R
import com.ayoprez.openscores.domain.models.SportSelectionListItem
import com.ayoprez.openscores.domain.viewmodels.SportSelectionViewModel
import com.ayoprez.openscores.ui.theme.OpenScoresTheme

@Composable
fun SportSelectionScreen(
    navController: NavController,
    model: SportSelectionViewModel = hiltViewModel()
) {
    App {
        ChooseSportScreen(model, navController = navController)
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
fun ChooseSportScreen(sportViewModel: SportSelectionViewModel, navController: NavController) {

    val sportItems = sportViewModel.sportsList.value

    Column(verticalArrangement = Arrangement.SpaceBetween) {
        ExplanationText()
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(sportItems.size) { index ->
                SelectionList(
                    items = sportItems,
                    index
                ) { sportViewModel.selectItem(sportItems[index]) }
            }
        }
        DoneButton(
            navController = navController,
            sportItems
        ) { sportViewModel.saveSelectedItems() }
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
fun SelectionList(
    items: List<SportSelectionListItem>,
    index: Int,
    onSelected: (SportSelectionListItem) -> Unit
) {
    Row {
        Text(
            items[index].title,
            modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp, start = 20.dp)
                .clickable { onSelected(items[index]) },
            style = TextStyle(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        if (items[index].isSelected) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "")
        }
    }
}

@Composable
fun DoneButton(
    navController: NavController,
    sportItems: List<SportSelectionListItem>,
    onButtonPressed: () -> Unit
) {
    OutlinedButton(
        onClick = {
            onButtonPressed.invoke()
            navigateToNextScreenWithSelectedItems(navController, sportItems)
        },
        modifier = Modifier.padding(top = 20.dp, start = 20.dp),
        enabled = sportItems.any { it.isSelected }
    ) {
        Text("Done")
    }
}

fun navigateToNextScreenWithSelectedItems(
    navController: NavController,
    sportItems: List<SportSelectionListItem>
) {
    val idList = sportItems.filter { item -> item.isSelected }
        .map { selectedItems -> selectedItems.id }.toTypedArray()
    navController.navigate(ScreenNavigation.GameScoresScreen.passValues(idList))

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