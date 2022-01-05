package com.ayoprez.openscores.domain.repository

import com.ayoprez.openscores.data.remote.response.AllGamesModel
import com.ayoprez.openscores.data.utils.ResponseHelper
import java.time.LocalDate

interface BasketballRepository {

    suspend fun getGamesByDate(date: LocalDate): ResponseHelper<AllGamesModel>
}