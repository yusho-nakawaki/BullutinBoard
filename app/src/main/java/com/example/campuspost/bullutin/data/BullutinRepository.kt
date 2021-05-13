package com.example.campuspost.bullutin.data

class BullutinRepository private constructor(private val bullutinDao: BullutinDao) {

    fun addPost(post: Bullutin) {
        bullutinDao.addPost(post)
    }

    suspend fun getPosts() = bullutinDao.getPosts()


    companion object {
        @Volatile private var instance: BullutinRepository? = null
        fun getInstance(bullutinDao: BullutinDao) = instance ?: synchronized(this) {
            instance ?: BullutinRepository(bullutinDao).also { instance = it }
        }
    }
}