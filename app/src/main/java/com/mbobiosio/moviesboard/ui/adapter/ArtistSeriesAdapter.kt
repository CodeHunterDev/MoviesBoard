package com.mbobiosio.moviesboard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.moviesboard.databinding.ItemArtistSeriesBinding
import com.mbobiosio.moviesboard.model.cast.SeriesCast

class ArtistSeriesAdapter(private val seriesClickListener: ((SeriesCast) -> Unit)?) :
    ListAdapter<SeriesCast, ArtistSeriesAdapter.ArtistSeriesVH>(SeriesItemCallback()) {

    private class SeriesItemCallback : DiffUtil.ItemCallback<SeriesCast>() {
        override fun areItemsTheSame(oldItem: SeriesCast, newItem: SeriesCast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SeriesCast, newItem: SeriesCast): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistSeriesAdapter.ArtistSeriesVH {
        val binding = ItemArtistSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistSeriesVH(binding)
    }

    override fun onBindViewHolder(holder: ArtistSeriesAdapter.ArtistSeriesVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ArtistSeriesVH(private val binding: ItemArtistSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(seriesCast: SeriesCast) = with(itemView) {
            binding.seriesCast = seriesCast
            binding.executePendingBindings()

            setOnClickListener {
                seriesClickListener?.invoke(seriesCast)
            }
        }
    }
}