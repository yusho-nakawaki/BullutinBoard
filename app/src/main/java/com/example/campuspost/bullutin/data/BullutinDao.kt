package com.example.campuspost.bullutin.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BullutinDao {

    private var postList = mutableListOf<Bullutin>()

//    init {
//        posts.value = postList
//    }

    fun addPost(post: Bullutin) {
        postList.add(post)
    }

    suspend fun getPosts(): List<Bullutin>? {

        return suspendCoroutine { continuation ->
            val ref = FirebaseFirestore.getInstance()

            val docRef = ref.collection("posts_mirrativ").document("mirrativ").collection("android")
            docRef.orderBy("time", Query.Direction.DESCENDING).get()
                    .addOnSuccessListener { documents ->
                        if (documents != null) {
                            Log.d("bullutin", "DocumentSnapshot data: ${documents}")

                            postList = mutableListOf<Bullutin>()
                            for (document in documents) {
                                Log.d("bullutincell", "DocumentSnapshot data: ${document}")
                                val data = document.data as Map<String, Any>
                                val email1 = data["email"] as String
                                val name1 = data["name"] as String
                                val postId1 = data["postId"] as String
                                val time1 = data["time"] as String
                                val message1 = data["message"] as String
                                val photoUrlOp1 = data["photoUrl"] as List<String>
                                /*
                            -------------------
                            val isRemessageOp1 = data["isRemessage"] as String
                             -------------------
                             */
                                val isCommentOp1 = data["isComment"] as String
                                val good1 = data["good"] as Long
                                val goodList1 = data["goodList"] as List<String>
                                /*
                            -----------
                            val remessageList = data["remessageList"] as List<String>
                            -----------
                             */
                                val remessage1 = data["remessage"] as Long
                                val comment1 = data["comment"] as Long

                                var photoUrl1: List<String>? = null
                                var isRemessage1: String? = null
                                var isComment1: String? = null
                                if (photoUrlOp1[0] != "nil") {
                                    photoUrl1 = photoUrlOp1
                                }
//                        if (isRemessageOp1 != "nill") { isRemessage1 = isRemessageOp1)
//                        if (isCommentOp1 != "nill") { isComment1 = isCommentOp1 }
                                val fetchPost = Bullutin(email1, name1, time1, postId1, message1, photoUrl1, null, null, good1.toInt(), goodList1.toTypedArray(), 0, goodList1.toTypedArray(), 0)
                                postList.add(fetchPost)
                            }
                            continuation.resume(postList)
                        }
                        // fetch no data
                        else {
                            Log.d("bullutin", "No such document")
                            continuation.resume(postList)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("bullutin", "get failed with ", exception)
                        continuation.resume(postList)
                    }

        }

//        postList.add(Bullutin("email", "88888888", "2021/05/03 12:00:00", "postId", "ここに投稿したメッセージが現れるよ。ワクワク。。。ここに投稿したメッセージが現れるよ。ワクワク。。。ここに投稿したメッセージが現れるよ。ワクワク。。。", null, null, null, 20, arrayOf("0_nil"), 10, arrayOf("0_nil"), 0))
    }

}