package com.cerminnovations.moviesboard.data.remote.model.search

import com.cerminnovations.moviesboard.data.remote.model.response.Response
import com.squareup.moshi.Json

/*
* Created by Mbuodile Obiosio on Jan 04, 2021.
* Twitter: @cazewonder
* Nigeria
*/
data class SearchResult(
    @Json(name = "adult")
    val isAdult: Boolean?,
    @Json(name = "gender")
    val gender: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "known_for")
    val knownFor: List<KnownFor>?,
    @Json(name = "known_for_department")
    val knownForDepartment: String?,
    @Json(name = "media_type")
    val mediaType: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "popularity")
    val popularity: Double?,
    @Json(name = "profile_path")
    val profilePath: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "video")
    val video: Boolean?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Int?,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "origin_country")
    val originCountry: List<String>?,
    @Json(name = "original_name")
    val originalName: String?
) : Response
