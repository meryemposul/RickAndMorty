package com.meryem.rickandmorty

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meryem.rickandmorty.adapter.EpisodeAdapter
import com.meryem.rickandmorty.api.RetrofitClient
import com.meryem.rickandmorty.api.RickAndMortyService
import com.meryem.rickandmorty.model.EpisodeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.episodeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EpisodeAdapter()
        recyclerView.adapter = adapter

        loadEpisodes()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    private fun loadEpisodes() {
        val service = RetrofitClient.retrofitInstance.create(RickAndMortyService::class.java)
        val call = service.getAllEpisodes(1)

        call.enqueue(object : Callback<EpisodeResponse> {
            override fun onResponse(call: Call<EpisodeResponse>, response: Response<EpisodeResponse>) {
                if (response.isSuccessful) {
                    val episodeList = response.body()?.results
                    if (episodeList != null) {
                        adapter.setData(episodeList)
                    }
                }
            }

            override fun onFailure(call: Call<EpisodeResponse>, t: Throwable) {
                Log.e("MainActivity", "Error fetching episodes", t)
            }
        })
    }
}
