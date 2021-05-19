package com.example.campuspost.bullutin.utility

import java.lang.Exception

// outは引数としてではなく、戻り値として型を指定
sealed class MyResult<out R> {
    data class Success<out T>(val data: T) : MyResult<T>()
    data class Error(val exception: Exception) : MyResult<Nothing>()
}