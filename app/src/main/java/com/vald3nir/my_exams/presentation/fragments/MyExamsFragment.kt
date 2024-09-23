package com.vald3nir.my_exams.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.request.RequestOptions
import com.vald3nir.core.extensions.views.setupDefaultLayoutManager
import com.vald3nir.core.presentation.CoreFragment
import com.vald3nir.core.presentation.components.adapters.CustomDifferAdapter
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.databinding.FragmentMyExamsBinding
import com.vald3nir.my_exams.databinding.ItemViewExamsBinding
import com.vald3nir.my_exams.domain.diffs.examsDiff
import com.vald3nir.my_exams.presentation.MainViewModel
import com.vald3nir.my_exams.presentation.extensions.bindItem

class MyExamsFragment : CoreFragment(R.layout.fragment_my_exams) {

    private lateinit var binding: FragmentMyExamsBinding
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    private val dashboardAdapter by lazy {
        val adapter = CustomDifferAdapter(bindingInflater = ItemViewExamsBinding::inflate,
            list = emptyList(),
            itemDiffUtil = examsDiff(),
            onBind = { item, binding, _, _ ->
                binding.bindItem(item) { redirectToEditExam(it) }
            })
        adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyExamsBinding.bind(view)
        initViews()
        loadExams()
    }

    private fun loadExams() = with(binding) {
        viewModel.exams.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                txvExamsNotFound.isVisible = true
                rvExams.isVisible = false
            } else {
                txvExamsNotFound.isVisible = false
                rvExams.isVisible = true
                dashboardAdapter.submitList(it.toMutableList())
            }
        }
        viewModel.loadExams()
    }

    private fun initViews() = with(binding) {

        viewModel.googleUserDTO?.let {
            txvUserName.text = it.userName
            ivUserProfile.loadImage(
                url = it.profileImageUrl,
                placeholder = R.drawable.ic_logo,
                effect = RequestOptions.circleCropTransform()
            )
        }

        rvExams.setupDefaultLayoutManager()
        rvExams.adapter = dashboardAdapter

        btnNewExam.setOnClickListener { redirectToCreateNewExam() }

        txvLogout.text = viewModel.logoutTextButton(requireContext())
        txvLogout.setOnClickListener { logout() }
    }

    private fun logout() {
        showLoading(true)
        postSimpleDelayed(callback = {
            viewModel.logout(this) { redirectToLogin() }
        }, 3000L)
    }

    private fun redirectToLogin() {
        navigateTo(MyExamsFragmentDirections.actionMoveToStartFragment())
    }

    private fun redirectToCreateNewExam() {
        viewModel.currentExamDTO = null
        navigateTo(MyExamsFragmentDirections.actionMoveToCreateNewExamFragment())
    }

    private fun redirectToEditExam(exam: ExamDTO?) {
        viewModel.currentExamDTO = exam
        navigateTo(MyExamsFragmentDirections.actionMoveToEditExamFragment())
    }

}