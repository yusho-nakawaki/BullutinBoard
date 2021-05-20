package com.example.campuspost.bullutin.data

//変数名がよくわからない
//自分だけわかるような変数はよくない
//今回だけはios版(昔作った自分だけしかわからないアプリ)と合わせるためにこのままにします
data class Bullutin(
        val email: String,
        val name: String,
        val time: String,
        val postId: String,
        val message: String,
        val photoUrl: List<String>?,
        val isRemessagePostId: String?, //親のpostId
        val isCommentPostId: String?, //親のpostId
        var good: Int,
        var goodList: Array<String>, //いいねした人達のemail
        var remessage: Int,
        var remessageList: Array<String>, //子のpostIds
        var comment: Int,
)
