package ru.geekbrain.android.mvp_mvvm

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.geekbrain.android.mvp_mvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var  binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener{
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString())
        }
    }

    private  fun restorePresenter(): LoginPresenter{
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }


    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
            binding.loginButton.isVisible = false
            binding.loginEditText.isVisible = false
            binding.passwordEditText.isVisible = false
            binding.root.setBackgroundColor(Color.GREEN)
    }

    @MainThread
    override fun setError(error: String) {

        Toast.makeText(this, "ERROR $error", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        binding.loginButton.isEnabled = false
        hideKeyboard(this)
    }

    @MainThread
    override fun hideProgress() {
            binding.loginButton.isEnabled = true

    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

    @SuppressLint("ServiceCast")
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