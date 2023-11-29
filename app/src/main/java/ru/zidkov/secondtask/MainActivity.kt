package ru.zidkov.secondtask

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import java.util.Date


class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var db: MainDb
    private lateinit var pref: SharedPreferences

    private val refresh_key: String = "refresh_time"
    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Важное замечание: по-видимому метод inflate вызывается внутри setContentView
        setContentView(R.layout.activity_main)

        val date = Date().time

        pref = getSharedPreferences("second_task", MODE_PRIVATE)

        val editor: SharedPreferences.Editor = pref.edit()

        db = MainDb.getDb(this)

        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        vm.send(MainViewModel.MainEvent.LoadEvent(db))

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