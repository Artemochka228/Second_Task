package ru.zidkov.secondtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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

        val names = listOf("Кот", "Жак Фреско", "Евгений Понасенков", "Poggers", "Я)")

        val titles = listOf("Кот", "Великий мыслитель", "Классная походка", "Пог", "Дединсайдик")

        val imageSources =
            listOf(
                R.drawable.kitya,
                R.drawable.fresko,
                R.drawable.ponasenkov,
                R.drawable.poggers,
                R.drawable.doomer
            )

        val phoneAdapter: PhoneAdapter = PhoneAdapter(this, names, titles, imageSources)

        usersList.adapter = phoneAdapter
        // Определение обработчика нажатий для элементов ListView
        usersList.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view: View?, i: Int, l: Long) {
                val intent: Intent = SecondActivity.getIntent(context, names[i], titles[i], imageSources[i])
                startActivity(intent)
            }
        }

    }

}