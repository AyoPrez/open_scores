package com.ayoprez.openscores.data.repositories

import com.ayoprez.openscores.data.data_source.SportsDao
import com.ayoprez.openscores.domain.models.SportSelectionListItem
import com.ayoprez.openscores.domain.repository.SportsRepository

class SportsRepositoryImpl(
    private val sportsDao: SportsDao
): SportsRepository {

    override suspend fun getSelectedSports(): List<SportSelectionListItem> {
        return sportsDao.getSelectedSport()
    }

    override suspend fun getSportById(id: String): SportSelectionListItem? {
        return sportsDao.getSportById(id)
    }

    override suspend fun saveSelectedSports(sports: List<SportSelectionListItem>) {
        sportsDao.saveSelectedSports(sports)
    }

}