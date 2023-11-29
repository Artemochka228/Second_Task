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
        data class LoadEvent(val db: MainDb) : MainEvent()
        data class SecondActEvent(val user: User) : MainEvent()
    }

    val mainActEventLiveData = SingleLiveEvent<MainEvent>()

    private val stateLiveMutable: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val stateLive: LiveData<MainState> get() = stateLiveMutable


    fun send(event: MainEvent) {
        when (event) {
            is MainEvent.LoadEvent -> load(event.db)
            is MainEvent.SecondActEvent -> mainActEventLiveData.setValue(event)
            else -> {}
        }
    }

    private fun queryToDb(db: MainDb) {
        val list = mutableListOf<User>()
        viewModelScope.launch(Dispatchers.IO) {
            val characters: List<Item> = db.getDao().getAllItems()
            list.addAll(characters.map{
                User(
                    it.name,
                    it.species,
                    it.image
                )
            })
            stateLiveMutable.postValue(MainState(list))
        }
    }

    private fun load(db: MainDb) {
        viewModelScope.launch(Dispatchers.IO) {
            db.getDao().deleteAllItems()
            db.getDao().resetPrimaryKey()
            if (db.getDao().getAllItems().isEmpty()){
                for (j in 1..42) {
                    val character = characterApi.getAllCharacters(j)
                    character.characters.forEach { char ->
                        val item = Item(null,
                            char.name,
                            char.species,
                            char.image
                        )
                        db.getDao().insertItem(item)
                    }
                }
            }
            queryToDb(db)
        }

    }
}
