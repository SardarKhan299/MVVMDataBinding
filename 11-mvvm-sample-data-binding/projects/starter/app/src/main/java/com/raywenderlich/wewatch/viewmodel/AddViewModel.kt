package com.raywenderlich.wewatch.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raywenderlich.wewatch.data.MovieRepository
import com.raywenderlich.wewatch.data.MovieRepositoryImpl
import com.raywenderlich.wewatch.data.model.Movie

class AddViewModel(private val repository: MovieRepository = MovieRepositoryImpl()): ViewModel()  {

  var title = ObservableField<String>("")
  var releaseDate = ObservableField<String>("")

  //1
  private val saveLiveData = MutableLiveData<Boolean>()
  //2
  fun getSaveLiveData(): LiveData<Boolean> = saveLiveData
  //3
  fun saveMovie() {
    Log.d(AddViewModel::class.simpleName, "saveMovie: ")
    if (canSaveMovie()) {
      repository.saveMovie(Movie(title = title.get(), releaseDate =
      releaseDate.get()))
      saveLiveData.postValue(true)
    } else {
      saveLiveData.postValue(false)
    }
  }
  //4
  fun canSaveMovie(): Boolean {
    val title = this.title.get()
    Log.d(AddViewModel::class.simpleName, "canSaveMovie: $title ")
    title?.let {
      return title.isNotEmpty()
    }
    return false
  }

  }