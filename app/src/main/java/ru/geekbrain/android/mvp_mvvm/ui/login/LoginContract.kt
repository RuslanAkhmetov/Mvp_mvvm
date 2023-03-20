package ru.geekbrain.android.mvp_mvvm.ui.login

import android.os.Handler
import androidx.annotation.MainThread

class LoginContract{

    interface  View{
        @MainThread
        fun setSuccess()
        @MainThread
        fun setError(error: String)
        @MainThread
        fun showProgress()
        @MainThread
        fun hideProgress()
        @MainThread
        fun getHandler(): Handler
        @MainThread
        fun showToast(message: String)
    }

    interface Presenter{
        fun onAttach(view: View)
        fun onCredentialsChange()
        fun onLogin(login: String, password: String)
        fun onForgotPassword(login: String)
        fun onRegister(login: String, password: String, fullName:String)
    }
}