package ru.zidkov.secondtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Важное замечание: по-видимому метод inflate вызывается внутри setContentView
        setContentView(R.layout.activity_main)

        val context: Context = this

        val usersList: RecyclerView = findViewById(R.id.usersList)

        // определяем слушатель нажатий для RecyclerView
        val userClickListener: UserAdapter.OnUserClickListener =
            object : UserAdapter.OnUserClickListener {
                override fun onUserClick(user: User, postition: Int) {
                    val (name, title, imageSource) = user
                    val intent: Intent = SecondActivity.getIntent(context, name, title, imageSource)
                    startActivity(intent)
                }
            }

        val users: MutableList<User> =
            mutableListOf(
                User("Жак Фреско", "Великий мыслитель", R.drawable.fresko),
                User("Кот", "Кот", R.drawable.kitya),
                User("Евгений Понасенков", "Классная походка", R.drawable.ponasenkov),
                User("Poggers", "Пог", R.drawable.poggers),
                User("Я)", "Дединсайдик", R.drawable.doomer)
            )

        val userAdapter = UserAdapter(this, users, userClickListener)

        usersList.adapter = userAdapter
    }

}