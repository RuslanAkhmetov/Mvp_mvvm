package ru.geekbrain.android.mvp_mvvm

import android.app.Application
import android.content.Context
import ru.geekbrain.android.mvp_mvvm.data.MockLoginApiImpl
import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI

class App:Application() {
    val loginAPI: LoginAPI by lazy { MockLoginApiImpl() }
}

val Context.app: App
    get() { return applicationContext as App}


