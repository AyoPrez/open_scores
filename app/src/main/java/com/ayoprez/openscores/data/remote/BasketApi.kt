package com.ayoprez.openscores.data.remote

import com.ayoprez.openscores.data.remote.response.AllGamesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BasketApi {

    //The dates in this query should have the format 'YYYY-MM-DD'
    @GET("games")
    suspend fun getAllGamesInDate(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String
    ): AllGamesModel
}