package com.task.ui.base.listeners

import com.example.campuspost.bullutin.data.Bullutin


// protocol
interface RecyclerItemListener {
    fun onItemSelected(post : Bullutin)
}
