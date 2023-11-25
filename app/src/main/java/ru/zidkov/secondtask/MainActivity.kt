package ru.zidkov.secondtask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Важное замечание: по-видимому метод inflate вызывается внутри setContentView
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this).get(MainViewModel::class.java)

        vm.send(LoadEvent())

        val usersList: RecyclerView = findViewById(R.id.usersList)

        val userAdapter = UserAdapter(this, fun(user: User, position: Int) {
            val (name, title, imageSource) = user
            val intent: Intent = SecondActivity.getIntent(this, name, title, imageSource)
            startActivity(intent)
        })
        vm.stateLive.observe(this) { list ->
            userAdapter.users = list.userList
        }

        usersList.adapter = userAdapter
    }

}