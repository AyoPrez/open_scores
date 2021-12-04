package com.ayoprez.openscores.model

import androidx.lifecycle.ViewModel
import com.ayoprez.openscores.data.remote.BasketApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor (
    private val api: BasketApi
        ): ViewModel() {


}