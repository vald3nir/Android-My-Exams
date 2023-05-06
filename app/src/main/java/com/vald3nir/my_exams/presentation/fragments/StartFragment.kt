package com.vald3nir.my_exams.presentation.fragments

import androidx.fragment.app.activityViewModels
import com.vald3nir.core.R
import com.vald3nir.core.presentation.CoreFragment
import com.vald3nir.my_exams.presentation.MainViewModel

class StartFragment : CoreFragment(R.layout.empty_layout) {

    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onResume() {
        super.onResume()
        loadUserGoogle()
    }

    private fun loadUserGoogle() {
        viewModel.loadUserGoogle(
            this,
            onError = { authenticateUser() },
            onSuccess = { checkDatabase() }
        )
    }

    private fun checkDatabase() {
        viewModel.checkDatabase { redirectToHome() }
    }

    private fun authenticateUser() {
        viewModel.authenticateUser(this)
    }

    private fun redirectToHome() {
        navigateTo(StartFragmentDirections.actionMoveToExamsFragment())
    }

}