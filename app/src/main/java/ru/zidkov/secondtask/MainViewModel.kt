package ru.zidkov.secondtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val stateLiveMutable: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val stateLive: LiveData<MainState> = stateLiveMutable

    fun send(event: MainEvent) {
        when(event) {
            is LoadEvent -> load()
            else -> TODO()
        }
    }

    private fun load() {
        if (stateLiveMutable.value == null) {
            stateLiveMutable.value = MainState(Generator.generateItemsData())
        }
    }
}