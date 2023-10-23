package ru.zidkov.secondtask

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar

class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val arguments: Bundle? = intent.extras
        val name: String? = arguments?.getString("name")
        val title: String? = arguments?.getString("title")
        val imageSource: Int? = arguments?.getInt("imageSource")

        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolBar.setTitle(title)

        val nameView: TextView = findViewById(R.id.name)
        nameView.setText(name)

        val titleView: TextView = findViewById(R.id.title)
        titleView.setText(title)

        val imageView: ImageView = findViewById(R.id.image)
        if (imageSource != null) {
            imageView.setImageResource(imageSource)
        }

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
        return super.onOptionsItemSelected(item);
    }

    fun onClick(view: View) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}