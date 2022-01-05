package com.ayoprez.openscores.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SportSelectionListItem(
    @PrimaryKey val id: String,
    val title: String,
    var isSelected: Boolean
)
