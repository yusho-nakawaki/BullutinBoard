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

//    val allContainer: LinearLayout = view.findViewById(R.id.allcontainer_bullutin)
//    var userPicuture: ImageView = view.findViewById(R.id.imageview_bullutin_main)
//    var userName: TextView = view.findViewById(R.id.username_textview_bullutin)
//    var postTime: TextView = view.findViewById(R.id.posttime_textview_bullutin)
//    var postMessage: TextView = view.findViewById(R.id.post_message_bullutin)
//    val photoContainer: LinearLayout = view.findViewById(R.id.image_container_bullutin)
//    val photo1: ImageView = view.findViewById(R.id.image1_bullutin)
//    val photo2: ImageView = view.findViewById(R.id.image2_bullutin)
//    val photo3: ImageView = view.findViewById(R.id.image3_bullutin)
//    val photo4: ImageView = view.findViewById(R.id.image4_bullutin)
//    val remessageContainer: View = view.findViewById(R.id.repeat_container_bullutin)
//    val goodButton: ImageButton = view.findViewById(R.id.goodbutton_bullutin)
//    val goodCountLabel: TextView = view.findViewById(R.id.goodcount_bullutin)
//    val repeatButton: ImageButton = view.findViewById(R.id.repeatbutton_bullutin)
//    val repeatCountLabel: TextView = view.findViewById(R.id.repeatcount_bullutin)
//    val commentButton: ImageButton = view.findViewById(R.id.commentbutton_bullutin)
//    val commentCountLabel: TextView = view.findViewById(R.id.commentcount_bullutin)
//    val otherButton: ImageButton = view.findViewById(R.id.otherbutton_bullutin)

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