package com.mbobiosio.moviesboard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.moviesboard.databinding.ItemMovieBinding
import com.mbobiosio.moviesboard.model.movies.Movie


class MovieAdapter(val listener: (Movie) -> Unit) :
    ListAdapter<Movie, MovieAdapter.MovieAdapterVH>(ListItemCallback()) {


    private class ListItemCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)

        return MovieAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapterVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MovieAdapterVH(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) = with(itemView) {
            binding.movie = movie
            binding.executePendingBindings()

            setOnClickListener {
                listener.invoke(movie)
            }
        }
    }
}

/*
override fun getItemCount() = dataList.size

override fun onBindViewHolder(holder: MovieAdapterVH, position: Int) =
    holder.bind(dataList[position])

fun setData(data: List<Movie>) {
    this.dataList = data
    notifyDataSetChanged()
}

inner class MovieAdapterVH(private val binding: ItemMovieBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) = with(itemView)  {
        binding.movie = movie
        binding.executePendingBindings()

        setOnClickListener {
            listener.invoke(movie)
        }
    }
}
*/
