package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class TestRetrofit {

    fun getService(): MovieService {
        return getRetrofit().create(MovieService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())
            .baseUrl(BASE_URL)
            .build()
    }

    private fun getInterceptor(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor {
                it.proceed(
                    it.request().newBuilder().apply {
                        addHeader("X-Naver-Client-Id", "hxJDK9LHUFtNfAmFSbTj")
                        addHeader("X-Naver-Client-Secret", "nHXzGoDbez")
                    }.build()
                )
            }
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    companion object {
        private const val BASE_URL = "https://openapi.naver.com/"
    }
}

interface MovieService {

    @GET("v1/search/movie.json")
    fun getMovieDto(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int
    ): Call<MovieDto>
}

data class MovieDto(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>
) {
    data class Item(
        val title: String,
        val link: String,
        val image: String,
        val subtitle: String,
        val pubDate: String,
        val director: String,
        val actor: String,
        val userRating: Int
    )
}