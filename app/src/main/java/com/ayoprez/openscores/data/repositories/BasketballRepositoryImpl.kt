package com.ayoprez.openscores.data.repositories

import com.ayoprez.openscores.data.remote.BasketApi
import com.ayoprez.openscores.data.remote.response.AllGamesModel
import com.ayoprez.openscores.data.utils.ResponseHelper
import com.ayoprez.openscores.domain.repository.BasketballRepository
import dagger.hilt.android.scopes.ActivityScoped
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

@ActivityScoped
class BasketballRepositoryImpl @Inject constructor(
    private val api: BasketApi
) : BasketballRepository {

    override suspend fun getGamesByDate(date: LocalDate): ResponseHelper<AllGamesModel> {
        val response = try {
            val pattern = "yyyy-MM-dd"
            val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
            val formattedDate = simpleDateFormat.format(date)

            api.getAllGamesInDate(formattedDate, formattedDate)
        } catch (e: Exception) {
            return ResponseHelper.Error(e)
        }

        return ResponseHelper.Success(response)
    }

}