package com.example.campuspost.bullutin.ui.adapter

import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campuspost.R
import com.example.campuspost.bullutin.data.Bullutin
import com.example.campuspost.databinding.BullutinBoardCellBinding
import com.squareup.picasso.Picasso
import com.task.ui.base.listeners.RecyclerItemListener

// swiftのtableViewCellの部分
class BullutinViewHolder(private val itemBinding: BullutinBoardCellBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        // layoutの初期設定
    }

    fun bind(postItem: Bullutin, recyclerItemListener: RecyclerItemListener) {
        itemBinding.allcontainerBullutin.setOnClickListener {
            recyclerItemListener.onItemSelected(postItem)
        }
        itemBinding.usernameTextviewBullutin.text = postItem.name
        itemBinding.posttimeTextviewBullutin.text = postItem.time
        itemBinding.postMessageBullutin.text = postItem.message


        itemBinding.imageviewBullutinMain.setImageURI(null)
        Picasso.get()
            .load("https://www.mirrativ.co.jp/images/ogp_mirra_logo.png")
            .resize(300, 300) //表示サイズ指定
            .centerCrop() //resizeで指定した範囲になるよう中央から切り出し
            .into(itemBinding.imageviewBullutinMain) //imageViewに流し込み

        itemBinding.goodcountBullutin.text = "${postItem.good}"
        itemBinding.repeatcountBullutin.text = "${postItem.remessage}"
        itemBinding.commentcountBullutin.text = "${postItem.comment}"

        if (postItem.photoUrl == null) {
            itemBinding.imageContainerBullutin.visibility = View.GONE
        } else {

        }

        if (postItem.isRemessagePostId == null) {
            itemBinding.repeatContainerBullutin.visibility = View.GONE
        } else {

        }
    }

}