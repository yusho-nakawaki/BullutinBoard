package com.example.campuspost.bullutin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.campuspost.bullutin.data.BullutinRepository

class BullutinViewModelFaculty(private val bullutinRepository: BullutinRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BullutinViewModel(bullutinRepository) as T
    }

}