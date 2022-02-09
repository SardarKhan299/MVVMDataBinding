package com.raywenderlich.wewatch.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.wewatch.data.MovieRepository
import com.raywenderlich.wewatch.data.MovieRepositoryImpl
import com.raywenderlich.wewatch.data.model.Movie

class MainViewModel(private val repository: MovieRepository = MovieRepositoryImpl()) : ViewModel() {

  private val allMovies = MediatorLiveData<List<Movie>>()

  init {
    Log.d(MainViewModel::class.simpleName, "init: ")
    getAllMovies()
  }

  fun getSavedMovies() = allMovies

  private fun getAllMovies() {
    Log.d(MainViewModel::class.simpleName, "getAllMovies: ")
    allMovies.addSource(repository.getSavedMovies()) { movies ->
      allMovies.postValue(movies)
    }
  }

  fun deleteSavedMovies(movie: Movie) {
    repository.deleteMovie(movie)
  }
}