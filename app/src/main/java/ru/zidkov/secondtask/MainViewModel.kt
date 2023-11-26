package ru.zidkov.secondtask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
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
        data object LoadEvent: MainEvent()
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
            val list: MutableList<User> = mutableListOf()
            CoroutineScope(Dispatchers.IO).launch {
                for (i in 1..100) {
                    val character = characterApi.getCharacterById(i)
                    list.add(User(
                        character.name,
                        character.species,
                        character.image
                        )
                    )
                }
                stateLiveMutable.postValue(MainState(list))
            }
        }
    }
}