package com.raywenderlich.wewatch.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raywenderlich.wewatch.data.db.MovieDao
import com.raywenderlich.wewatch.data.model.Movie
import com.raywenderlich.wewatch.data.model.MoviesResponse
import com.raywenderlich.wewatch.data.net.RetrofitClient
import com.raywenderlich.wewatch.db
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MovieRepositoryImpl : MovieRepository {
    init {
        Log.d(MovieRepositoryImpl::class.simpleName, "init: ")
    }

    //1
    private val movieDao: MovieDao = db.movieDao()
    private val retrofitClient = RetrofitClient()
    private var allMovies: LiveData<List<Movie>>

    init {
        allMovies = movieDao.getAll()
    }

    override fun getSavedMovies() = allMovies

    override fun saveMovie(movie: Movie) {
        Log.d(MovieRepositoryImpl::class.simpleName, "saveMovie: ")
        thread {
            movieDao.insert(movie)
        }
    }

    override fun deleteMovie(movie: Movie) {
        Log.d(MovieRepositoryImpl::class.simpleName, "deleteMovie: ")
        thread {
            movieDao.delete(movie.id)
        }
    }

    override fun searchMovies(query: String): LiveData<List<Movie>?> {
        Log.d(MovieRepositoryImpl::class.simpleName, "searchMovies: ")
        val data = MutableLiveData<List<Movie>>()
        retrofitClient.searchMovies(query).enqueue(object :
                Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                data.value = null
                Log.d(this.javaClass.simpleName, "Failure")
            }
            override fun onResponse(call: Call<MoviesResponse>, response:
            Response<MoviesResponse>) {
                data.value = response.body()?.results
                Log.d(this.javaClass.simpleName, "Response: ${response.body()?.results}")
            }
        })
        return data
    }
}