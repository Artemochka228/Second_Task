package ru.zidkov.secondtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    sealed class MainEvent {
        object LoadEvent: MainEvent()
        data class SecondActEvent(val user: User): MainEvent()
    }

    val mainActEventLiveData = SingleLiveEvent<MainEvent>()

    private val stateLiveMutable: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val stateLive: LiveData<MainState> get() = stateLiveMutable


    fun send(event: MainEvent) {
        when(event) {
            is MainEvent.LoadEvent -> load()
            is MainEvent.SecondActEvent -> mainActEventLiveData.setValue(event)
            else -> {}
        }
    }

    private fun load() {
        if (stateLiveMutable.value == null) {
            stateLiveMutable.value = MainState(Generator.generateItemsData())
        }
    }
}