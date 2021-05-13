package com.example.campuspost.bullutin.ui

import androidx.lifecycle.ViewModel
import com.example.campuspost.bullutin.data.Bullutin
import com.example.campuspost.bullutin.data.BullutinRepository

class BullutinViewModel(private val bullutinRepository: BullutinRepository): ViewModel() {

    fun getPosts() = bullutinRepository.getPosts()
    fun addPost(post: Bullutin) = bullutinRepository.addPost(post)

    //ここにデータを置く
    // viewにはおかない
}