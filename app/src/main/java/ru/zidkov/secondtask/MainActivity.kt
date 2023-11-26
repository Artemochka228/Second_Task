package ru.zidkov.secondtask

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Важное замечание: по-видимому метод inflate вызывается внутри setContentView
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        vm.send(MainViewModel.MainEvent.LoadEvent)

        val usersList: RecyclerView = findViewById(R.id.usersList)

        val userAdapter = UserAdapter(this, vm)

        vm.stateLive.observe(this) { list ->
            userAdapter.users = list.userList
        }

        vm.mainActEventLiveData.observe(this) { event ->
            when(event) {
                is MainViewModel.MainEvent.SecondActEvent -> {
                    val (name, title, imageSource) = event.user
                    val intent: Intent = SecondActivity.getIntent(this, name, title, imageSource)
                    startActivity(intent)
                }
                else -> {}
            }
        }

        usersList.adapter = userAdapter
    }

}