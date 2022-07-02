package com.cerminnovations.moviesboard.presentation.moviedetail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.cerminnovations.core.base.BaseContract
import com.cerminnovations.core.base.BaseFragment
import com.cerminnovations.domain.model.movies.MovieData
import com.cerminnovations.domain.model.movies.MovieDetail
import com.cerminnovations.moviesboard.databinding.FragmentMovieDetailBinding
import com.cerminnovations.moviesboard.presentation.movies.UIState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding>(
        FragmentMovieDetailBinding::inflate
    ),
    BaseContract {

    private val viewModel by viewModels<MovieDetailViewModel>()

    private lateinit var movieItem: MovieData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = requireArguments()
        movieItem = MovieDetailFragmentArgs.fromBundle(args).movieDetail
    }

    override fun setupViews() {
        observeData()

        initViews()

        getMovieDetails()
    }

    private fun initViews() = with(binding) {
        viewLifecycleOwner.lifecycle.addObserver(youTubePlayer)
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetail(movieItem.movieId, "", "images, reviews, credits, videos")
    }

    override fun observeData() = with(binding) {
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> {
                    Timber.d("$it")
                }
                is UIState.Success -> {
                    movieDetailsLayout.movie = it.result
                    handleVideo(it.result)
                }
                is UIState.Error -> {
                    Timber.d("Error ${it.message?.errorMessage}")
                }
            }
        }
    }

    private fun handleVideo(movieDetail: MovieDetail?) {
        movieDetail?.let {
            it.videoResponse?.results?.forEach { video ->
                Timber.d("Video ${video.name}")
                handleYoutubePlayer(video.key)
            }
        }
    }

    private fun handleYoutubePlayer(key: String) = with(binding) {
        youTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo(key, 0f)
            }
        })
    }

    override fun showProgress(isVisible: Boolean) {
    }

    override fun showError(isError: Boolean, error: String?) {
    }
}
