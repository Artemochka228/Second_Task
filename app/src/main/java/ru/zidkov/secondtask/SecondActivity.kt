package ru.zidkov.secondtask

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class SecondActivity : AppCompatActivity() {
    companion object {
        private const val Name = "name"
        private const val Title = "title"
        private const val ImageSrc = "imageSource"
        fun getIntent(
            context: Context,
            name: String,
            title: String,
            imageSource: String
        ): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(Name, name)
                putExtra(Title, title)
                putExtra(ImageSrc, imageSource)
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.e("AAA", "Я создан")

        val arguments: Bundle? = intent.extras
        val name: String? = arguments?.getString(Name)
        val title: String? = arguments?.getString(Title)
        val imageSource: String? = arguments?.getString(ImageSrc)

        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolBar.title = title

        val nameView: TextView = findViewById(R.id.name)
        nameView.text = name

        val titleView: TextView = findViewById(R.id.title)
        titleView.text = title

        val imageView: ImageView = findViewById(R.id.image)
        Glide
            .with(this)
            .load(imageSource)
            .into(imageView)

        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}