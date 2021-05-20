package com.example.campuspost.bullutin.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.campuspost.bullutin.utility.MyResult
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class InsertPostViewModel(): ViewModel() {

    suspend fun insertPostToFirestore(postText: String, photoUrlArray: List<String>?)
    : MyResult<Boolean> {
        return suspendCoroutine { continuation ->
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
            val docRef = ref
                    .collection("posts_mirrativ")
                    .document("mirrativ")
                    .collection("android")

            docRef.document(time).set(postData)
                    .addOnSuccessListener {
                        Log.d("bullutin", "addData")
                        continuation.resume(MyResult.Success(true))
                    }
                    .addOnFailureListener {
                        Log.d("bullutin", "failed")
                        continuation.resume(MyResult.Error(it))
                    }
        }
    }

}