package ru.geekbrain.android.mvp_mvvm.data

import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI

class MockLoginApiImpl: LoginAPI {
    override fun login(login: String, password: String): Boolean {
        return login == password
    }

    override fun register(login: String, password: String, fullName: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun logout(): Boolean {
        TODO("Not yet implemented")
    }

    override fun restorePassword() {
        TODO("Not yet implemented")
    }
}