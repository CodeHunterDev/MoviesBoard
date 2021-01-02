package com.mbobiosio.moviesboard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mbobiosio.moviesboard.api.RetrofitClient
import com.mbobiosio.moviesboard.service.ArtistType
import com.mbobiosio.moviesboard.service.paging.artist.ArtistsDataSource

class ArtistsViewModel : ViewModel() {
    private val apiService = RetrofitClient.apiService

    private val artistDataSource = ArtistsDataSource(
        apiService,
        ArtistType.POPULAR
    )

    private val pagingConfig = PagingConfig(
        pageSize = 20, initialLoadSize = 10, enablePlaceholders = true
    )

    val artistPaging = Pager(pagingConfig) {
        artistDataSource
    }.flow.cachedIn(viewModelScope)
}