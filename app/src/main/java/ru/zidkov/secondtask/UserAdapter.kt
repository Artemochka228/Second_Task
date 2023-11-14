package ru.zidkov.secondtask

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(
    private val context: Context,
    clickListen: (User, Int) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var onClickListener = clickListen
    var users: List<User> = listOf<User>()
        set(value) {
            field = value
        }

    // LayoutInflater - объект, позволяющий пропарсить файл с xml разметкой
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view: View = inflater.inflate(R.layout.item, parent, false)
        return UserAdapter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = users.get(position)
        holder.bind(user, context)

        // обработка нажатия
        holder.itemView.setOnClickListener {
            // вызываем метод слушателя передавая ему данные
            onClickListener(user, position)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.unbind()
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var title: TextView
        private lateinit var image: ImageView
        private lateinit var name: TextView

        fun bind(user: User, context: Context) {
            title = itemView.findViewById(R.id.title)
            image = itemView.findViewById(R.id.avatar)
            name = itemView.findViewById(R.id.name)

            Glide
                .with(context)
                .load(user.imageSource)
                .into(image)
            title.setText(user.title)
            name.setText(user.name)
        }

        fun unbind() {
            itemView.setOnClickListener(null)
        }
    }
}