package com.cerminnovations.moviesboard.data.remote.api

import com.cerminnovations.moviesboard.data.remote.model.artists.Artist
import com.cerminnovations.moviesboard.data.remote.model.artists.ArtistInfo
import com.cerminnovations.moviesboard.data.remote.model.movie.Movie
import com.cerminnovations.moviesboard.data.remote.model.movie.MovieDetails
import com.cerminnovations.moviesboard.data.remote.model.response.BaseResponse
import com.cerminnovations.moviesboard.data.remote.model.search.SearchResult
import com.cerminnovations.moviesboard.data.remote.model.shows.Series
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    /*Movies*/
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("append_to_response") appendToResponse: String?
    ): MovieDetails

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
        @Query("region") region: String?
    ): BaseResponse<Movie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
        @Query("region") region: String?
    ): BaseResponse<Movie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
        @Query("region") region: String?
    ): BaseResponse<Movie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?,
        @Query("region") region: String?
    ): BaseResponse<Movie>

    /*Trending*/
    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingMovies(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): BaseResponse<Movie>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingSeries(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): BaseResponse<Series>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingArtists(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): BaseResponse<Artist>

    /*TV Series*/

    /*Artists*/
    @GET("person/popular")
    suspend fun getPopularArtists(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): BaseResponse<Artist>

    @GET("person/{person_id}")
    suspend fun getArtistById(
        @Path("person_id") personId: Int?,
        @Query("api_key") apiKey: String?,
        @Query("append_to_response") appendToResponse: String?
    ): ArtistInfo

    /*Search*/
    @GET("search/multi")
    suspend fun search(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("include_adult") isAdult: Boolean
    ): BaseResponse<SearchResult>
}
