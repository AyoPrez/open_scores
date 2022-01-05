package com.ayoprez.openscores.domain.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoprez.openscores.domain.models.SportSelectionListItem
import com.ayoprez.openscores.domain.repository.SportsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportSelectionViewModel @Inject constructor(
    private val sportsRepository: SportsRepository
): ViewModel() {

    val sportsList: MutableState<List<SportSelectionListItem>> = mutableStateOf(listOf())
    private var sports = listOf<SportSelectionListItem>()

    init {
        viewModelScope.launch {
            if(sportsRepository.getSelectedSports().isEmpty()) {
                sportsRepository.saveSelectedSports(mapSports())

                sportsList.value = mapSports()
                sports = mapSports()
            } else {
                sportsList.value = sportsRepository.getSelectedSports()
                sports = sportsRepository.getSelectedSports()
            }
        }
    }

    //TODO Replace by a logic that check what sports has urls in the system
    private fun mapSports(): List<SportSelectionListItem> {
        val sportsMap = mutableListOf("Football", "Basketball").map {
            SportSelectionListItem(
                id = it.length.toString(),
                title = it,
                isSelected = false
            )
        }

        return sportsMap
    }

    // Events
    fun selectItem(item: SportSelectionListItem) {
        val index = sportsList.value.indexOf(item)
        sports[index].isSelected = !item.isSelected
        sportsList.value = emptyList()
        sportsList.value = sports
    }

    fun saveSelectedItems() {
        viewModelScope.launch {
            sportsRepository.saveSelectedSports(sports)
        }
    }
}