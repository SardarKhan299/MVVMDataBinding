package com.raywenderlich.wewatch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.raywenderlich.wewatch.data.MovieRepository
import com.raywenderlich.wewatch.data.MovieRepositoryImpl
import com.raywenderlich.wewatch.data.model.Movie

class AddViewModel(private var movieRepository: MovieRepository = MovieRepositoryImpl()):ViewModel(){
    init {
        Log.d(AddViewModel::class.simpleName, "init: ")
    }
    fun saveMovie(movie: Movie){
        Log.d(AddViewModel::class.simpleName, "saveMovie: ")
        movieRepository.saveMovie(movie)
    }
}