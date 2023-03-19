package ru.geekbrain.android.mvp_mvvm

import java.lang.Thread.sleep

class LoginPresenter: LoginContract.Presenter {
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
                if (checkCredentials(login, password)) {
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