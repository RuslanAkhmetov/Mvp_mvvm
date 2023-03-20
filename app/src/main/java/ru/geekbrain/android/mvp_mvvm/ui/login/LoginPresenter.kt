package ru.geekbrain.android.mvp_mvvm.ui.login

import ru.geekbrain.android.mvp_mvvm.domain.LoginAPI
import ru.geekbrain.android.mvp_mvvm.ui.login.LoginContract
import java.lang.Thread.sleep

class LoginPresenter(val loginAPI: LoginAPI) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private var currentResult = false
    private var errorText =""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if(currentResult){
            view.setSuccess()
        } else {
            view.setError(errorText)
        }
    }

    override fun onLogin(login: String, password: String ) {
        view?.showProgress()
        Thread{
            sleep(3_000)
            view?.getHandler()?.post {
                view?.hideProgress()
                val success = loginAPI.login(login, password)
                if (success) {
                    view?.setSuccess()
                    currentResult = true
                    errorText =""
                } else {
                    view?.setError("Invalid login")
                    currentResult = false
                    errorText ="Invalid login"
                }
            }
        }.start()

    }

    private fun checkCredentials(login: String, password: String) =
        login == password

    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}