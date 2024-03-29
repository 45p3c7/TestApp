package com.example.testapp.data

import java.lang.Exception

sealed class Result<out R> {
    class Success<out T>(val data : T) : Result<T>()
    class Error(val exception: Exception) : Result<Nothing>()
}