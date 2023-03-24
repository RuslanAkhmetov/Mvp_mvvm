package ru.geekbrain.android.mvp_mvvm.data

import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI
import ru.geekbrain.android.mvp_mvvm.domain.LoginUserCase

class MockUserCaseImpl(private val loginAPI: LoginAPI
                       ): LoginUserCase {

    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread {
            val result = loginAPI.login(login, password)
            callback(result)
        }.start()

    }

    override fun restorePassword(login: String, callback: (Boolean) -> Unit) {
        Thread{
            val result = loginAPI.restorePassword(login)
            callback(result)
        }.start()
    }

    override fun register(
        login: String,
        password: String,
        fullName: String,
        callback: (Boolean) -> Unit
    ) {
        Thread{
            val result = loginAPI.register(login, password, fullName)
            callback(result)

        }.start()
    }
}