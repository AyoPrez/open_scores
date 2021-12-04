package com.ayoprez.openscores.repository

import com.ayoprez.openscores.data.remote.response.AllGamesModel
import com.ayoprez.openscores.utils.ResponseHelper
import java.time.LocalDate

interface BasketballRepository {

    suspend fun getGamesByDate(date: LocalDate): ResponseHelper<AllGamesModel>
}