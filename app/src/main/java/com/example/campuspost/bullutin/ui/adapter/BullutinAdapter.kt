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
import com.example.campuspost.databinding.BullutinBoardCellBinding
import com.task.ui.base.listeners.RecyclerItemListener


// swiftで言うと、tableViewのextensionのところ
class BullutinAdapter(private val context: Context,
//                      private val itemClickListener: BullutinViewHolder.ItemClickListener,
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
//        val layoutInflater = LayoutInflater.from(context)
//        val mView = layoutInflater.inflate(R.layout.bullutin_board_cell, parent, false)
        val itemBinding = BullutinBoardCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BullutinViewHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: BullutinViewHolder, position: Int) {
        holder?.let {
            holder.bind(itemList[position], onItemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}
