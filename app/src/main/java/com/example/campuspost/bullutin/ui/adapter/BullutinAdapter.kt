package com.example.campuspost.bullutin.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.campuspost.R
import com.example.campuspost.bullutin.data.Bullutin
import com.example.campuspost.bullutin.ui.BullutinViewModel
import com.task.ui.base.listeners.RecyclerItemListener


// swiftで言うと、tableViewのextensionのところ
class BullutinAdapter(private val context: Context,
                      private val itemClickListener: BullutinViewHolder.ItemClickListener,
                      private val itemList:List<Bullutin>,
                      private val bullutinViewModel: BullutinViewModel
                      )
    : RecyclerView.Adapter<BullutinViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(post: Bullutin) {
            bullutinViewModel.startBullutinItem(post)
        }
    }

    private var mRecyclerView : RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BullutinViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.bullutin_board_cell, parent, false)

        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return BullutinViewHolder(mView)
    }


    override fun onBindViewHolder(holder: BullutinViewHolder, position: Int) {


        holder?.let {

            it.postMessage.text = itemList[position].message
            it.postTime.text = itemList[position].time
            it.userName.text = itemList[position].name
//            it.userPicuture.setImageURI(null)
//            it.userPicuture.setImageURI(Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Ftwitter.com%2Fminami373hamabe&psig=AOvVaw0tzCDl99uTtf2aV0JMOGKt&ust=1620362756287000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCJi7juaftPACFQAAAAAdAAAAABAD"))
            it.goodCountLabel.text = "${itemList[position].good}"
            it.repeatCountLabel.text = "${itemList[position].remessage}"
            it.commentCountLabel.text = "${itemList[position].comment}"

            if (itemList[position].photoUrl == null) {
                it.photoContainer.visibility = View.GONE
            } else {

            }

            if (itemList[position].isRemessagePostId == null) {
                it.remessageContainer.visibility = View.GONE
            } else {

            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}
