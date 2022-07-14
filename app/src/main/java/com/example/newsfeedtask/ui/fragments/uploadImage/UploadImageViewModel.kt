package com.example.newsfeedtask.ui.fragments.uploadImage

import androidx.lifecycle.*

import com.example.newsfeedtask.model.NewsItem
import com.example.newsfeedtask.network.entities.ResponseStatus
import com.example.newsfeedtask.repository.FavNewsRepository
import com.example.newsfeedtask.repository.NewsRepository
import com.example.newsfeedtask.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
@ExperimentalCoroutinesApi
@HiltViewModel
class UploadImageViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val favNewsRepository: FavNewsRepository
    ) :ViewModel(){



    private val _dataUploadImageState:MutableLiveData<DataState<ResponseStatus>> = MutableLiveData()
    val dataUploadImageState:LiveData<DataState<ResponseStatus>>
        get() = _dataUploadImageState

    fun setStateEvent(mainStateEvent: MainStateEvent, pageNumber:Int=0, newsItem: NewsItem? = null ,file:File? = null){

        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.UploadFileEvent -> {
                    newsRepository.uploadImage(file!!).onEach { dataState ->
                        _dataUploadImageState.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }
}
sealed class MainStateEvent{
    object UploadFileEvent: MainStateEvent()


}