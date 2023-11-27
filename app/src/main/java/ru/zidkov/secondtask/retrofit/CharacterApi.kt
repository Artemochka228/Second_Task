package ru.zidkov.secondtask.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int): ListCharacters
}