package ru.geekbrain.android.mvp_mvvm.domain

interface LoginUserCase {

    fun login(login: String, password: String, callback: (Boolean) -> Unit)
}