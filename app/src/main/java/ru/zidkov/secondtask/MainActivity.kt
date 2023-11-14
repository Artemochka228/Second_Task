package ru.zidkov.secondtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Важное замечание: по-видимому метод inflate вызывается внутри setContentView
        setContentView(R.layout.activity_main)

        val context: Context = this

        val usersList: RecyclerView = findViewById(R.id.usersList)

        val userAdapter = UserAdapter(this, fun(user: User, position: Int) {
            val (name, title, imageSource) = user
            val intent: Intent = SecondActivity.getIntent(context, name, title, imageSource)
            startActivity(intent)
        })

        usersList.adapter = userAdapter
    }

}