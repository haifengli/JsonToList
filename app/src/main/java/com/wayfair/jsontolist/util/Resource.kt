package com.wayfair.jsontolist.util

/**
 * Generic class which holds a value with its status.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?) : Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String) : Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}