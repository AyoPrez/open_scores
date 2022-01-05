package com.ayoprez.openscores.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayoprez.openscores.domain.models.SportSelectionListItem

@Database(entities = [SportSelectionListItem::class], version = 1)
abstract class SportsDB: RoomDatabase() {
    abstract val sportsDao: SportsDao

    companion object {
        const val DATABASE_NAME = "Sports_DB"
    }
}