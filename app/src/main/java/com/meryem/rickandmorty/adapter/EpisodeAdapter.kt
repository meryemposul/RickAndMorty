package com.meryem.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meryem.rickandmorty.R
import com.meryem.rickandmorty.model.Episode

class EpisodeAdapter : RecyclerView.Adapter<EpisodeAdapter.ViewHolder>() {

    private val episodes: MutableList<Episode> = mutableListOf()

    fun setData(newEpisodes: List<Episode>) {
        episodes.clear()
        episodes.addAll(newEpisodes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val episodeNameTextView: TextView = itemView.findViewById(R.id.episodeNameTextView)
        private val episodeAirDateTextView: TextView = itemView.findViewById(R.id.episodeAirDateTextView)

        fun bind(episode: Episode) {
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.air_date
        }
    }
}
