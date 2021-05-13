package com.example.campuspost.bullutin.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.campuspost.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_insert_post.*
import java.text.SimpleDateFormat
import java.util.*

class InsertPost : AppCompatActivity() {

    private var photoUrlArray: List<String>? = null

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

                insertImageToStorage()
            }
        }

        return super.onOptionsItemSelected(item)
    }


    private fun insertImageToStorage() {
        if (photoUrlArray == null) {
            insertPostToFirestore()
        }
        else {

        }
    }

    private fun insertPostToFirestore() {
        val postText: String = textview_insertpost.text.toString()
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date()
        val time = df.format(date)

        var photoURL: List<String> = listOf("nil")
        photoUrlArray?.let {
            photoURL = it
        }

        val postData: Map<String, Any> = mapOf(
            "email" to "myEmail",
            "postId" to "postId" + time,
            "name" to "Mirrativ",
            "message" to postText,
            "time" to time,
            "isRemessage" to listOf<String>("nil"),
            "photoUrl" to listOf<String>("nil"),
            "isComment" to "nil",
            "good" to 0,
            "goodList" to listOf<String>("0_nil"),
            "remessage" to 0,
            "remessageList" to listOf<String>("0_nil"),
            "comment" to 0,
        )

        val ref = FirebaseFirestore.getInstance()
        val docRef = ref.collection("posts_mirrativ").document("mirrativ").collection("android")
        docRef.document(time).set(postData)
            .addOnSuccessListener {
                Log.d("bullutin", "addData")


                val result = Intent()
                result.putExtra("message", "Hello! I'm from SubActivity!⛄️")
                setResult(Activity.RESULT_OK, result)
                finish()

        }.addOnFailureListener {
            Log.d("bullutin", "failed")
        }
    }
}