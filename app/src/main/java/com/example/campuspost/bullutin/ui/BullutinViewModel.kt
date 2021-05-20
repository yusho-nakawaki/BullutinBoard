package com.example.campuspost.bullutin.ui

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.campuspost.bullutin.data.Bullutin
import com.example.campuspost.bullutin.data.BullutinRepository
import kotlinx.coroutines.launch

    class BullutinViewModel(private val bullutinRepository: BullutinRepository): ViewModel() {

//    suspend fun getPosts() = bullutinRepository.getPosts()
//    fun addPost(post: Bullutin) = bullutinRepository.addPost(post)


    //ここにデータを置く
    // viewにはおかない
    private val _posts = MutableLiveData<List<Bullutin>>()
    val posts: LiveData<List<Bullutin>> get() = _posts

    //画面遷移用
    private val _openItemBullutin = MutableLiveData<Bullutin>()
    val openItemBullutin: LiveData<Bullutin> get() = _openItemBullutin


    fun getPosts() {
        viewModelScope.launch {
            _posts.value = bullutinRepository.getPosts()
        }
    }

    fun startBullutinItem(post: Bullutin) {
        _openItemBullutin.value = post
    }

}