package com.example.campuspost.bullutin.utility

import com.example.campuspost.bullutin.data.BullutinDao
import com.example.campuspost.bullutin.data.BullutinDatabase
import com.example.campuspost.bullutin.data.BullutinRepository
import com.example.campuspost.bullutin.ui.BullutinViewModelFaculty

object BullutinInjectorUtils {

    fun provideBullutinViewModelFaculty(): BullutinViewModelFaculty {
        val bullutinRepository = BullutinRepository.getInstance(BullutinDatabase.getInstance().bulltinDao)
        return BullutinViewModelFaculty(bullutinRepository)
    }
}