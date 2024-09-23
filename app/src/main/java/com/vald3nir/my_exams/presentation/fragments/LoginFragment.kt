package com.vald3nir.my_exams.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.vald3nir.core.presentation.CoreFragment
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.databinding.FragmentLoginBinding
import com.vald3nir.my_exams.presentation.MainViewModel

class LoginFragment : CoreFragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        setupButtons()
    }

    private fun setupButtons() = with(binding) {
        btnGoogleLogin.setup(this@LoginFragment)
        btnDemoLogin.setOnClickListener { viewModel.runLiveDemo(){ redirectToHome() } }
    }

    override fun onResume() {
        super.onResume()
        loadUserGoogle()
    }

    private fun loadUserGoogle() {
        viewModel.loadUserGoogle(
            this@LoginFragment,
            onError = { showLoading(false) },
            onSuccess = { checkDatabase() }
        )
    }

    private fun checkDatabase() {
        viewModel.checkDatabase { redirectToHome() }
    }

    private fun redirectToHome() {
        navigateTo(LoginFragmentDirections.actionMoveToExamsFragment())
    }
}