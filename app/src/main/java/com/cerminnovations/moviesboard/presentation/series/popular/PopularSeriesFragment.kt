package com.cerminnovations.moviesboard.presentation.series.popular

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.cerminnovations.core.base.BaseContract
import com.cerminnovations.core.base.BaseFragment
import com.cerminnovations.domain.listeners.SeriesItemClickListener
import com.cerminnovations.domain.model.series.TvSeries
import com.cerminnovations.moviesboard.R
import com.cerminnovations.moviesboard.databinding.FragmentSeriesBinding
import com.cerminnovations.moviesboard.presentation.series.SeriesAdapter
import com.cerminnovations.moviesboard.presentation.seriesdetail.SeriesDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@AndroidEntryPoint
class PopularSeriesFragment :
    BaseFragment<FragmentSeriesBinding>(
        FragmentSeriesBinding::inflate
    ),
    BaseContract {

    private val viewModel by viewModels<PopularTvViewModel>()
    private val seriesAdapter by lazy {
        SeriesAdapter()
    }

    override fun setupViews() {
        // init recycler view
        initRecyclerView()

        observeData()
    }

    private fun initRecyclerView() = with(binding) {
        series.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = seriesAdapter
        }

        seriesAdapter.itemClickListener = object : SeriesItemClickListener {
            override fun onItemClick(seriesInfo: TvSeries?) {
                seriesInfo?.let {
                    findNavController().navigate(
                        R.id.seriesDetailFragment,
                        SeriesDetailFragmentArgs(seriesInfo).toBundle()
                    )
                }
            }
        }
    }

    override fun observeData() {
        viewModel.getPopularSeries().observe(viewLifecycleOwner) {
            seriesAdapter.submitData(lifecycle, it)
        }
    }

    override fun showProgress(isVisible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showError(isError: Boolean, error: String?) {
        TODO("Not yet implemented")
    }
}
