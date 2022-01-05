package com.ayoprez.openscores.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayoprez.openscores.domain.models.SportSelectionListItem

@Dao
interface SportsDao {

    //1 = true; 0 = false
    @Query("SELECT * FROM sportselectionlistitem WHERE isSelected = 1")
    suspend fun getSelectedSport(): List<SportSelectionListItem>

    @Query("SELECT * FROM sportselectionlistitem WHERE id= :id")
    suspend fun getSportById(id: String): SportSelectionListItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSelectedSports(sports: List<SportSelectionListItem>)
}