package ru.geekbrain.android.mvp_mvvm.ui.login

import ru.geekbrain.android.mvp_mvvm.domain.LoginUserCase

class LoginPresenter(val userCase: LoginUserCase) : LoginContract.Presenter {
    private var view: LoginContract.View? = null
    private var currentResult = false
    private var errorText =""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if(currentResult){
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String ) {
        view?.showProgress()

        userCase.login(login, password){result:Boolean ->
            view?.hideProgress()
            if (result) {
                view?.setSuccess()
                currentResult = true
                errorText =""
            } else {
                view?.setError("Invalid login")
                currentResult = false
                errorText ="Invalid login"
            }

        }

    }

    private fun checkCredentials(login: String, password: String) =
        login == password

    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}