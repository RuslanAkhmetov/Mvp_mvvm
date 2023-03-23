package ru.geekbrain.android.mvp_mvvm.ui.login

import ru.geekbrain.android.mvp_mvvm.domain.LoginUserCase
import ru.geekbrain.android.mvp_mvvm.utils.Publisher

class LoginViewModel(val loginUserCase: LoginUserCase) : LoginContractMVVM.ViewModel {

    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> =  Publisher()
    override val toastText: Publisher<String?> = Publisher()

    override fun onLogin(login: String, password: String ) {

        shouldShowProgress.post(true)

        loginUserCase.login(login, password){ result:Boolean ->
            shouldShowProgress.post(false)
            if (result) {
                isSuccess.post(true)
                errorText.post("")
            } else {
                isSuccess.post(false)
                errorText.post("Invalid login")
            }

        }

    }

    override fun onForgotPassword(login: String) {
        loginUserCase.restorePassword(login){
            if(it){
                toastText.post("Letter with new Password was sent")
            } else {
                toastText.post("Login $login incorrect")
            }
        }
    }

    override fun onRegister(login: String, password: String, fullName: String) {
        loginUserCase.register(login, password, fullName){
            if(it){
                toastText.post("User $fullName was registered")
            }else{
                toastText.post("Login or/and password incorrect")
            }
        }
    }


    override fun onCredentialsChange() {
        TODO("Not yet implemented")
    }
}