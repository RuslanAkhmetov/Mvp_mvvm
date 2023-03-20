package ru.geekbrain.android.mvp_mvvm.domain

import androidx.annotation.WorkerThread

interface LoginAPI {
    @WorkerThread
    fun login (login: String, password: String):Boolean

    @WorkerThread
    fun register (login: String, password: String, fullName: String): Boolean

    @WorkerThread
    fun logout(): Boolean

    @WorkerThread
    fun restorePassword()

}