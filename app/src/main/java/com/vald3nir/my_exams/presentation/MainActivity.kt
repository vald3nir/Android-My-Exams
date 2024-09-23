package com.vald3nir.my_exams.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.databinding.ActivityMainBinding
import com.vald3nir.core.presentation.animations.TypeAnimation
import com.vald3nir.core.presentation.CoreActivity
import com.vald3nir.core.presentation.components.screens.LoadingScreenDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : CoreActivity() {

    private var loadingScreenDialog: LoadingScreenDialog? = null

    override fun showError(e: Exception?) {
        showLoading(false)
        e?.let {
            it.printStackTrace()
            showMessage(it.message)
        }
    }

    override fun showMessage(msg: String?) {
        showLoading(false)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading(show: Boolean) {
        postSimpleDelayed(delayMillis = 1000, callback = {
            loadingScreenDialog?.dismiss()
            if (show) {
                loadingScreenDialog = LoadingScreenDialog(
                    context = this,
                    title = getString(R.string.loading),
                    icon = R.drawable.ic_logo,
                    typeAnimation = TypeAnimation.HEART_BEAT
                )
                loadingScreenDialog?.show()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupUIStateObserver(viewModel)
    }

    override fun onStop() {
        super.onStop()
        showLoading(false)
    }
}