package com.meryem.rickandmorty.api


import com.meryem.rickandmorty.model.EpisodeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("episode")
    fun getAllEpisodes(@Query("page") page: Int): Call<EpisodeResponse>
}
