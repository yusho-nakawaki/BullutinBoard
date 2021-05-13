package com.example.campuspost.bullutin.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.campuspost.R
import com.example.campuspost.ui.main.RecyclerAdapterBullutin
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class BullutinDao {

    private var postList = mutableListOf<Bullutin>()
    private val posts = MutableLiveData<List<Bullutin>>()

    init {
        posts.value = postList
    }

    fun addPost(post: Bullutin) {
        postList.add(post)
        posts.value = postList
    }

    fun getPosts(): MutableLiveData<List<Bullutin>> {

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

//                    val bullutinRecyclerView = view?.findViewById<RecyclerView>(R.id.recycleview_bullutin)!!
//                    viewAdapter = RecyclerAdapterBullutin(view?.context!!, this, posts)
//                    recyclerView = bullutinRecyclerView.apply {
//                        // use this setting to improve performance if you know that changes
//                        // in content do not change the layout size of the RecyclerView
//                        setHasFixedSize(true)
//
//                        // use a linear layout manager
//                        layoutManager = viewManager
//
//                        // specify an viewAdapter (see also next example)
//                        adapter = viewAdapter
//                    }


                    posts.value = postList
                } else {
                    Log.d("bullutin", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("bullutin", "get failed with ", exception)
            }

//        postList.add(Bullutin("email", "mirrative", "2021/05/03 12:00:00", "postId", "ここに投稿したメッセージが現れるよ。ワクワク。。。ここに投稿したメッセージが現れるよ。ワクワク。。。ここに投稿したメッセージが現れるよ。ワクワク。。。", null, null, null, 20, arrayOf("0_nil"), 10, arrayOf("0_nil"), 0))
        return posts
    }

}