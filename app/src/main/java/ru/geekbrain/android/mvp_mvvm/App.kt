package ru.geekbrain.android.mvp_mvvm

import android.app.Application
import android.content.Context
import ru.geekbrain.android.mvp_mvvm.data.MockLoginApiImpl
import ru.geekbrain.android.mvp_mvvm.data.MockUserCaseImpl
import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI
import ru.geekbrain.android.mvp_mvvm.domain.LoginUserCase

class App:Application() {
    private val loginAPI: LoginAPI by lazy { MockLoginApiImpl() }
    val userCase: LoginUserCase by lazy {MockUserCaseImpl(loginAPI)}
}

val Context.app: App
    get() { return applicationContext as App}


