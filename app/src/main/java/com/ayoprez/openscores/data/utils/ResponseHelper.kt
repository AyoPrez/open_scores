package com.ayoprez.openscores.data.utils

sealed class ResponseHelper<T> (val data: T? = null, val exception: Exception? = null) {
    class Success<T>(data: T): ResponseHelper<T>(data)
    class Error<T>(exception: Exception, data: T? = null): ResponseHelper<T>(data, exception)
    class Loading<T>(data: T? = null): ResponseHelper<T>(data)
}
