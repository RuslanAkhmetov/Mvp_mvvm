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

    override fun onForgotPassword(login: String) {
        userCase.restorePassword(login){
            if(it){
                view?.showToast("Letter with new Password was sent")
            } else {
                view?.showToast("Login $login incorrect")
            }
        }
    }

    override fun onRegister(login: String, password: String, fullName: String) {
        userCase.register(login, password, fullName){
            if(it){
                view?.showToast("User $fullName was registered")
            }else{
                view?.showToast("Login or/and password incorrect")
            }
        }
    }



    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}