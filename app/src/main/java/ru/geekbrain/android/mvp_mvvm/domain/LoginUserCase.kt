package ru.geekbrain.android.mvp_mvvm.domain

interface LoginUserCase {

    fun login(login: String, password: String, callback: (Boolean) -> Unit)

    fun restorePassword(login: String, callback: (Boolean) -> Unit)

    fun register(login: String, password: String, fullName:String, callback: (Boolean) -> Unit)
}