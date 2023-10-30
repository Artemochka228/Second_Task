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

class UserAdapter(
    private val context: Context,
    private val users: List<User>,
    private val onClickListener: OnUserClickListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val TAG = this.javaClass.simpleName

    interface OnUserClickListener {
        fun onUserClick(user: User, postition: Int)
    }

    // LayoutInflater - объект, позволяющий пропарсить файл с xml разметкой
    val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view: View = inflater.inflate(R.layout.item, parent, false)
        return UserAdapter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = users.get(position)
        holder.image.setImageResource(user.imageSource)
        holder.title.setText(user.title)
        holder.name.setText(user.name)

        // обработка нажатия
        holder.itemView.setOnClickListener(View.OnClickListener {
            Log.i(TAG, "Это моё сообщение для записи в журнале")
            // вызываем метод слушателя передавая ему данные
            onClickListener.onUserClick(user, position)
        })
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.avatar)
        val name: TextView = itemView.findViewById(R.id.name)
    }
}