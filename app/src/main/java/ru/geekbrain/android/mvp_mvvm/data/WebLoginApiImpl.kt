package ru.geekbrain.android.mvp_mvvm.data

import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI

class WebLoginApiImpl: LoginAPI {
    fun lol(){
        //todo
    }

    override fun login(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(login: String, password: String, fullName: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun restorePassword(login: String): Boolean {
        TODO("Not yet implemented")
    }
}