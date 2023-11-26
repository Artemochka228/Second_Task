package ru.zidkov.secondtask.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApi {
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Character
}