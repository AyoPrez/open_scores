package com.ayoprez.openscores.domain.repository

import com.ayoprez.openscores.domain.models.SportSelectionListItem

interface SportsRepository {

    suspend fun getSelectedSports(): List<SportSelectionListItem>

    suspend fun getSportById(id: String): SportSelectionListItem?

    suspend fun saveSelectedSports(sports: List<SportSelectionListItem>)
}