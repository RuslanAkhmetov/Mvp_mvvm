package ru.geekbrain.android.mvp_mvvm.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.geekbrain.android.mvp_mvvm.app
import ru.geekbrain.android.mvp_mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    private var viewModel: LoginContractMVVM.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = restoreViewModel()

        init()

        binding.loginButton.setOnClickListener{
            viewModel?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString())
        }

        viewModel?.shouldShowProgress?.subscribe { shouldShow ->
            if(shouldShow == true){
                showProgress()
            } else {
                hideProgress()
            }

        }

        viewModel?.isSuccess?.subscribe {
            if(it==true) {
                setSuccess()
            }
        }

        viewModel?.errorText?.subscribe { message ->
            if (message != null && message.length > 0){
                val success = viewModel?.isSuccess?.value
                if(success==false) {
                    setError(message)
                }
            }
        }

        binding.forgotPasswordButton.setOnClickListener {
            viewModel?.onForgotPassword(binding.loginEditText.text.toString())
        }

        binding.submitButton.setOnClickListener {
            viewModel?.onRegister(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.fullNameEditText.text.toString()
            )
            binding.loginButton.isEnabled = true
            init()

        }

        binding.registerButton.setOnClickListener {
            binding.fullNameEditText.isVisible = true
            binding.loginButton.isEnabled = false
            binding.submitButton.isVisible = true
            binding.submitButton.isEnabled = true
        }

        viewModel?.toastText?.subscribe { toastText ->
            if(toastText != null && toastText.length >0)
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
        }
    }

    private  fun restoreViewModel(): LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel ?: LoginViewModel(app.userCase)
    }

    private fun init(){
        binding.fullNameEditText.isVisible = false
        binding.submitButton.isVisible = false
        binding.submitButton.isEnabled = false
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    @MainThread
    private fun setSuccess() {
            binding.loginButton.isVisible = false
            binding.loginEditText.isVisible = false
            binding.passwordEditText.isVisible = false
            binding.fullNameEditText.isVisible = false
            binding.forgotPasswordButton.isVisible = false
            binding.registerButton.isVisible = false

            binding.root.setBackgroundColor(Color.GREEN)
    }

    @MainThread
    fun setError(error: String) {
        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    private fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
    }

    @MainThread
    private fun hideProgress() {
            binding.loginButton.isEnabled = true
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

}