package com.vald3nir.my_exams.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.databinding.FragmentCreateNewExamBinding
import com.vald3nir.my_exams.presentation.MainViewModel
import com.vald3nir.core.presentation.CoreFragment

class CreateNewExamFragment : CoreFragment(R.layout.fragment_create_new_exam) {

    private lateinit var binding: FragmentCreateNewExamBinding
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateNewExamBinding.bind(view)
        setupNavigator()
        setupFields()
        setupButton()
    }

    private fun setupNavigator() = with(binding.navigator) {
        enableClickEvents(activity)
        updateIconsCor(R.color.primary_color)
    }

    private fun setupFields() = with(binding.cpInputs) {
        setupExamFields(useEditMode = true)
    }

    private fun setupButton() = with(binding) {
        btnConfirm.setOnClickListener {
            val exam = cpInputs.examEdited()
            if (exam.date.isNullOrEmpty()) {
                showMessage(getString(R.string.warning_date_empty))
            } else {
                viewModel.insertExam(exam) { onBackPressed() }
            }
        }
    }
}