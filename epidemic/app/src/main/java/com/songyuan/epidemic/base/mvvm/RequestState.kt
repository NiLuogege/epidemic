package com.songyuan.epidemic.base.mvvm

/**
 * Created by niluogege on 2019/10/10.
 */
enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
}

data class RequestState<out T>(val status: Status, val data: T?, val error: Throwable? = null) {
    companion object {
        fun <T> loading(data: T? = null) = RequestState(Status.LOADING, data)

        fun <T> success(data: T? = null) = RequestState(Status.SUCCESS, data)

        fun <T> error(error: Throwable?,data: T? = null) = RequestState(Status.ERROR, data, error)
    }

    fun isLoading(): Boolean = status == Status.LOADING
    fun isSuccess(): Boolean = status == Status.SUCCESS
    fun isError(): Boolean = status == Status.ERROR
}