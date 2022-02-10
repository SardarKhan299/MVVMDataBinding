package com.raywenderlich.wewatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.wewatch.data.MovieRepository
import com.raywenderlich.wewatch.data.MovieRepositoryImpl
import com.raywenderlich.wewatch.data.model.Movie

class SearchViewModel (private var repository: MovieRepository = MovieRepositoryImpl()):ViewModel(){
    init {
        Log.d(SearchViewModel::class.simpleName, "init: ")
    }

    fun searchMovie(query: String): LiveData<List<Movie>?> {
        return repository.searchMovies(query)
    }
    fun saveMovie(movie: Movie) {
        repository.saveMovie(movie)
    }
}