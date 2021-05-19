package com.example.campuspost.bullutin.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.campuspost.R
import com.example.campuspost.bullutin.utility.MyResult
import kotlinx.android.synthetic.main.activity_insert_post.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class InsertPostAcitivity : AppCompatActivity() {

    private var photoUrlArray: List<String>? = null

    // viewModelに引数がない時に有効なやり方
    // 引数ありだと class FactoryをviewModelに用意する必要がある
    private val insertPostViewModel: InsertPostViewModel by lazy {
        ViewModelProvider(this)[InsertPostViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_post)

        // 最初にキャンセルされた結果をセットしておくことで、端末の戻るボタンに対応させる
        setResult(Activity.RESULT_CANCELED)
    }

    // 上メニューの設定
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_insert_post, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.menu_add_post -> {
                val postText: String = textview_insertpost.text.toString()
                Log.d("postText", postText)

                if (photoUrlArray == null) {
                    insertPostToFirestore()
                }
                else {
                    //写真付きの投稿はstorageに入れてから投稿
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }



    private fun insertPostToFirestore() = MainScope().launch(Dispatchers.Main) {
        when (val result = insertPostViewModel.insertPostToFirestore(
                textview_insertpost.text.toString(),
                null)) {
            is MyResult.Success<Boolean> -> finishActivity()
            else -> Log.e("insert activity", result.toString())
        }
    }

    private fun finishActivity() {
        //firestoreに送れたことを確認してfinish
        val result = Intent()
        result.putExtra("message", "Hello! I'm from SubActivity!⛄️")
        setResult(Activity.RESULT_OK, result)
        finish()
    }
}