package com.example.campuspost.bullutin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.campuspost.R
import kotlinx.android.synthetic.main.activity_item_bullutin.*


class ItemBullutinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_bullutin)

        val intent = intent
        val data = intent.getStringExtra("item-post")
        println(data)

        textView_bullutin_item.text = data
    }
}