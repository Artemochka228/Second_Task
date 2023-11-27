package ru.zidkov.secondtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.zidkov.secondtask.retrofit.CharacterApi

class MainViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val characterApi = retrofit.create(CharacterApi::class.java)

    sealed class MainEvent {
        data object LoadEvent : MainEvent()
        data class SecondActEvent(val user: User) : MainEvent()
    }

    val mainActEventLiveData = SingleLiveEvent<MainEvent>()

    private val stateLiveMutable: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val stateLive: LiveData<MainState> get() = stateLiveMutable


    fun send(event: MainEvent) {
        when (event) {
            is MainEvent.LoadEvent -> load()
            is MainEvent.SecondActEvent -> mainActEventLiveData.setValue(event)
            else -> {}
        }
    }

    private fun load() {
        val list: MutableList<User> = mutableListOf()
        if (stateLiveMutable.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                for (j in 1..42) {
                    val character = characterApi.getAllCharacters(j)
                    list.addAll(character.characters.map { char ->
                        User(
                            char.name,
                            char.species,
                            char.image
                        )
                    })
                }
                stateLiveMutable.postValue(MainState(list))
            }
        }
    }
}
