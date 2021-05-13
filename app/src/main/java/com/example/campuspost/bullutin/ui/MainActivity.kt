package com.example.campuspost.bullutin.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.campuspost.R
import com.example.campuspost.bullutin.data.Bullutin

class MainActivity : AppCompatActivity() {

    private val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.communityTab)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            val intent = Intent(this, InsertPost::class.java)
            startActivityForResult(intent, 9)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != 9) { return }

        if (resultCode == Activity.RESULT_OK && data != null) {
//            val message = data.getStringExtra("message")
//            println(message)
            sectionsPagerAdapter.placeholderFragment.addPost(Bullutin("email", "mirrative", "2021/05/03 12:00:00", "postId", "ここに投稿したメッセージが現れるよ。", null, null, null, 20, arrayOf("0_nil"), 10, arrayOf("0_nil"), 0))
        } else if(resultCode == Activity.RESULT_CANCELED) {

        }
    }
}