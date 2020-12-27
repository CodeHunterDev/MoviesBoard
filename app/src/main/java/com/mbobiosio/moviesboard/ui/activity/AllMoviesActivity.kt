package com.mbobiosio.moviesboard.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbobiosio.moviesboard.R
import com.mbobiosio.moviesboard.databinding.AllMoviesBinding
import com.mbobiosio.moviesboard.model.movies.Movie
import com.mbobiosio.moviesboard.service.MovieType
import com.mbobiosio.moviesboard.ui.adapter.AllMoviesAdapter
import com.mbobiosio.moviesboard.viewmodels.AllMoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AllMoviesActivity : AppCompatActivity(), (Movie) -> Unit {

    private val viewModel by viewModels<AllMoviesViewModel>()
    private lateinit var binding : AllMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.all_movies)
        binding.lifecycleOwner = this

        val adapter = AllMoviesAdapter(this)
        binding.allMovies.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this@AllMoviesActivity, 2)
        }

        lifecycleScope.launch {
            viewModel.getMoviesFlow(MovieType.POPULAR).collectLatest {
                adapter.submitData(it)
            }
        }

    }

    override fun invoke(movie: Movie) {

    }
}