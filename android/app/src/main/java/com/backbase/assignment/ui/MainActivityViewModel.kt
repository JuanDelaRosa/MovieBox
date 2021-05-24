package com.backbase.assignment.ui

import android.widget.ImageView
import androidx.lifecycle.*
import com.backbase.assignment.app.MovieboxApp
import com.backbase.domain.common.Result
import com.backbase.domain.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MainActivityViewModel(private val movieboxApp: MovieboxApp) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies = _popularMovies

    private val _nowPlaying = MutableLiveData<List<Movie>>()
    val nowPlaying = _nowPlaying

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var page = 1

    fun getPlayingNow(){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = movieboxApp.getNowPlayingUseCase.invoke()){
                is Result.Success ->{
                    nowPlaying.postValue(result.data)
                    _dataLoading.postValue(false)
                }
                is Result.Error ->{
                    loadFromDB(1)
                }
            }
        }
    }
    private fun loadFromDB(popular : Int){
        viewModelScope.launch {
            when(val result = movieboxApp.getFromLocalDB.invoke(popular)){
                is Result.Success ->{
                    if(popular == 1 || popular ==3)
                        popularMovies.postValue(result.data)
                    else nowPlaying.postValue(result.data)
                    _dataLoading.postValue(false)
                }
                is Result.Error ->{
                    _dataLoading.postValue(false)
                    if(popular == 2 || popular ==3)
                        popularMovies.postValue(emptyList())
                    else nowPlaying.postValue(emptyList())
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    fun getMostPopular(){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = movieboxApp.getPopularUseCase.invoke(page)){
                is Result.Success ->{
                    page++
                    popularMovies.postValue(result.data)
                    _dataLoading.postValue(false)
                }
                is Result.Error ->{
                    loadFromDB(2)
                }
            }
        }
    }

    fun usePicasso(view : ImageView, path:String){
        if(path.isNotEmpty()) {
            viewModelScope.launch {
                Picasso.with(view.context).load(path).into(view)
            }
        }
    }

    class MainActivityViewModelFactory(private val movieboxApp: MovieboxApp) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(movieboxApp) as T
        }
    }
}