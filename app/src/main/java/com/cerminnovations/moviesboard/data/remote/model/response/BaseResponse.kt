package com.cerminnovations.moviesboard.data.remote.model.response

import com.squareup.moshi.Json

data class BaseResponse<T>(

    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: List<T>,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Int

)