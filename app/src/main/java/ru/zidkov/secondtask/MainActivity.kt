package ru.zidkov.secondtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Важное замечание: по-видимому метод inflate вызывается внутри setContentView
        setContentView(R.layout.activity_main)

        val context: Context = this

        val usersList: ListView = findViewById(R.id.usersList)

        val users =
            listOf(
                User("Кот", "Кот", R.drawable.kitya),
                User("Жак Фреско", "Великий мыслитель", R.drawable.fresko),
                User("Евгений Понасенков", "Классная походка", R.drawable.ponasenkov),
                User("Poggers", "Пог", R.drawable.poggers),
                User("Я)", "Дединсайдик", R.drawable.doomer)
            )

        val phoneAdapter = UserAdapter(this, users)

        usersList.adapter = phoneAdapter
        // Определение обработчика нажатий для элементов ListView
        usersList.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, i, _ ->
                val(name, title, imageSource) = users[i]
                val intent: Intent = SecondActivity.getIntent(context, name, title, imageSource)
                startActivity(intent)
            }

    }

}