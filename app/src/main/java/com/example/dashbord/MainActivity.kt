package com.example.dashbord

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var dbHelper: DBHelper
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private lateinit var email: TextView
    private val channelID = "com.example.dashbord"
    private val description = "Test notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(applicationContext)
        viewPost()


    }

    private fun viewPost() {

        val postList = dbHelper.getPost(this)//what is this refer to
        // the toast that take context parameter then will display in main activity because we passed it here as this in the Main activity
        val adapter = PostAdapter(this, postList)
        rv = findViewById(R.id.rv)
        rv.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }
}