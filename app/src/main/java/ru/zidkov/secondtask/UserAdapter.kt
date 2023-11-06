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
    users: List<User>,
    clickListen: (User, Int) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val onClickListener = clickListen
    private val usersList = users

    // LayoutInflater - объект, позволяющий пропарсить файл с xml разметкой
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view: View = inflater.inflate(R.layout.item, parent, false)
        return UserAdapter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = usersList.get(position)
        Glide
            .with(context)
            .load(user.imageSource)
            .into(holder.image)
        holder.title.setText(user.title)
        holder.name.setText(user.name)

        // обработка нажатия
        holder.itemView.setOnClickListener(View.OnClickListener {
            // вызываем метод слушателя передавая ему данные
            onClickListener(user, position)
        })
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.avatar)
        val name: TextView = itemView.findViewById(R.id.name)
    }
}