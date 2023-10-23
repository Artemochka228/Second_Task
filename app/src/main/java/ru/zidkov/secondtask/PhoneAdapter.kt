package ru.zidkov.secondtask

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class PhoneAdapter(
    private val context: Context,
    private val names: List<String>,
    private val titles: List<String>,
    private val imageSources: List<Int>
) :
    ArrayAdapter<String>(context, R.layout.item, names) {

    // LayoutInflater - объект, позволяющий пропарсить файл с xml разметкой
    val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // inflate - метод, возвращающий объект View, в котором содержится иерархия View из файла разметки R.layout.item
        val view: View = inflater.inflate(R.layout.item, parent, false)
        // view.findViewById(R.id.name) - ищет элемент с id name в рамках view
        val name = view.findViewById<TextView>(R.id.name)
        name.setText(names[position])
        // view.findViewById(R.id.title) - ищет элемент с id title в рамках view
        val title = view.findViewById<TextView>(R.id.title)
        title.setText(titles[position])
        // view.findViewById(R.id.avatar) - ищет элемент с id avatar в рамках view
        val image = view.findViewById<ImageView>(R.id.avatar)
        image.setImageResource(imageSources[position])

        return view
    }
}