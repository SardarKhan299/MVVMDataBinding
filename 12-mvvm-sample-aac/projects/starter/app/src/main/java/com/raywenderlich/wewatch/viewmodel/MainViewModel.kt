package com.raywenderlich.wewatch.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.wewatch.data.MovieRepository
import com.raywenderlich.wewatch.data.MovieRepositoryImpl
import com.raywenderlich.wewatch.data.model.Movie

class MainViewModel(private var repository: MovieRepository = MovieRepositoryImpl()) :ViewModel(){
    //1
    private val allMovies = MediatorLiveData<List<Movie>>()
    //2
    init {
        Log.d(MainViewModel::class.simpleName, "init: ")
        getAllMovies()
    }
    //3
    fun getSavedMovies() = allMovies
    //4
    private fun getAllMovies() {
        Log.d(MainViewModel::class.simpleName, "getAllMovies: ")
        allMovies.addSource(repository.getSavedMovies()) { movies ->
            Log.d(MainViewModel::class.simpleName, "getAllMovies: Observer called ${movies.size}")
            allMovies.postValue(movies)
        }
    }
    //5
    fun deleteSavedMovies(movie: Movie) {
        repository.deleteMovie(movie)
    }
}