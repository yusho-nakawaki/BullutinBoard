package com.example.campuspost.bullutin.ui.adapter

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campuspost.R

// swiftのtableViewCellの部分
class BullutinViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int) {
            Log.d("viewHolder(cell)", "tapped")
        }
    }

    val allContainer: LinearLayout = view.findViewById(R.id.allcontainer_bullutin)
    var userPicuture: ImageView = view.findViewById(R.id.imageview_bullutin_main)
    var userName: TextView = view.findViewById(R.id.username_textview_bullutin)
    var postTime: TextView = view.findViewById(R.id.posttime_textview_bullutin)
    var postMessage: TextView = view.findViewById(R.id.post_message_bullutin)
    val photoContainer: LinearLayout = view.findViewById(R.id.image_container_bullutin)
    val photo1: ImageView = view.findViewById(R.id.image1_bullutin)
    val photo2: ImageView = view.findViewById(R.id.image2_bullutin)
    val photo3: ImageView = view.findViewById(R.id.image3_bullutin)
    val photo4: ImageView = view.findViewById(R.id.image4_bullutin)
    val remessageContainer: View = view.findViewById(R.id.repeat_container_bullutin)
    val goodButton: ImageButton = view.findViewById(R.id.goodbutton_bullutin)
    val goodCountLabel: TextView = view.findViewById(R.id.goodcount_bullutin)
    val repeatButton: ImageButton = view.findViewById(R.id.repeatbutton_bullutin)
    val repeatCountLabel: TextView = view.findViewById(R.id.repeatcount_bullutin)
    val commentButton: ImageButton = view.findViewById(R.id.commentbutton_bullutin)
    val commentCountLabel: TextView = view.findViewById(R.id.commentcount_bullutin)
    val otherButton: ImageButton = view.findViewById(R.id.otherbutton_bullutin)

    init {
        // layoutの初期設定する
    }

}