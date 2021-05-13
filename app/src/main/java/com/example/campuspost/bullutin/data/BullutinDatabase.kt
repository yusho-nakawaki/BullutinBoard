package com.example.campuspost.bullutin.data

class BullutinDatabase private constructor() {

    val bulltinDao = BullutinDao()

    companion object {
        @Volatile private var instance: BullutinDatabase? = null

        // synchronize: 排他制御
        // このBullutinDatabaseをロックして、同時にインスタンスが複数作られないようにする
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: BullutinDatabase().also { instance = it }
        }
    }
}