package ru.geekbrain.android.mvp_mvvm.data

import android.os.Handler
import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI
import ru.geekbrain.android.mvp_mvvm.domain.LoginUserCase

class MockUserCaseImpl(private val loginAPI: LoginAPI,
                       private val handler: Handler): LoginUserCase {

    override fun login(login: String, password: String, callback: (Boolean) -> Unit) {
        Thread{
            val result = loginAPI.login(login, password)
            handler.post {
                callback(result)
            }
        }.start()

    }
}