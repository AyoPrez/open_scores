package com.ayoprez.openscores.domain.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayoprez.openscores.domain.repository.BasketballRepository
import com.ayoprez.openscores.domain.repository.SportsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val sportsRepository: SportsRepository,
    private val basketRepo: BasketballRepository
) : ViewModel() {

    private val _state = mutableStateOf("")
    val sportName: State<String> = _state

    fun getSportNameById(sportId: String) {
        viewModelScope.launch {
            _state.value = sportsRepository.getSportById(sportId)?.title ?: ""
        }
    }

}