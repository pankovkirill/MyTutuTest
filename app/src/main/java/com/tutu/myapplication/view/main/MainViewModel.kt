package com.tutu.myapplication.view.main

import androidx.lifecycle.LiveData
import com.tutu.myapplication.model.data.AppState
import com.tutu.myapplication.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(isOnline) }
    }

    private suspend fun startInteractor(isOnline: Boolean) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(interactor.getData(isOnline))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value =
            AppState.Success(null)
        super.onCleared()
    }
}