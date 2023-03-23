package ru.geekbrain.android.mvp_mvvm.ui.login

import ru.geekbrain.android.mvp_mvvm.utils.Publisher

class LoginContractMVVM{

    /**
     * MVP
     * Проблемы
     * 1) Восстановление состояния
     * 2) Сильная связанность
     * 3) Много промежуточного кода
     *(M) <-(P)<->(V)
     *
     * MVVM - Model View ViewModel
     * (M) <-(VM)<-(V)
     *
     *
      */

/* Интерфейс View больше не нужне в MVVM
его роль передается подпискам в ViewMode
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
    }*/

    /*
    * class Activity{
    * fun onCreate(){
    *   viewModel.shouldShowProgree.subscribe{it->
    *     if(it){
    *       dialog.show()
    *    } else {
    *       dialog.dismiss()
    *
    *   }
    *
    *   }
    * }
    * }
     */

    interface ViewModel{
        val shouldShowProgress:Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>
        val toastText: Publisher<String?>

        fun onCredentialsChange()
        fun onLogin(login: String, password: String)
        fun onForgotPassword(login: String)
        fun onRegister(login: String, password: String, fullName:String)
    }
}