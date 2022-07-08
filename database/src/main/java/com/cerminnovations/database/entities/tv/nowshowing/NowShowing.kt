package com.cerminnovations.database.entities.tv.nowshowing

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Entity(tableName = "now_showing_tv")
data class NowShowing(
    val tvId: Long,
    @PrimaryKey
    val name: String,
    val posterPath: String?,
    val popularity: Double,
    val backdropPath: String?,
    val voteAverage: Double,
    val overview: String,
    val firstAirDate: String?,
    val originCountry: List<String>,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val voteCount: Int,
    val originalName: String
)
