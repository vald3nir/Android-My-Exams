package com.vald3nir.my_exams.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.vald3nir.core.presentation.CoreFragment
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.databinding.FragmentEditExamsBinding
import com.vald3nir.my_exams.presentation.MainViewModel

class EditExamFragment : CoreFragment(R.layout.fragment_edit_exams) {

    private lateinit var binding: FragmentEditExamsBinding
    private val viewModel: MainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditExamsBinding.bind(view)
        setupNavigator()
        setupFields()
        setupButton()
    }

    private fun setupNavigator() = with(binding.navigator) {
        enableClickEvents(activity)
        updateIconsCor(R.color.primary_color)
    }

    private fun setupFields() = with(binding.cpInputs) {
        viewModel.currentExamDTO?.let { exam ->
            setupExamFields(exam, useEditMode = true)
        }
    }

    private fun setupButton() = with(binding) {
        btnDelete.setOnClickListener {
            showDeleteExamDialog {
                val exam = cpInputs.examEdited()
                viewModel.deleteExam(exam) {
                    onBackPressed()
                }
            }
        }
        btnConfirm.setOnClickListener {
            val exam = cpInputs.examEdited()
            viewModel.updateExam(exam) {
                onBackPressed()
            }
        }
    }

    private fun showDeleteExamDialog(onClickConfirm: () -> Unit) {
        showYesNoDialog(
            title = getString(R.string.dialog_delete_exam_title),
            message = getString(R.string.dialog_delete_exam_message),
            textConfirmButton = getString(R.string.dialog_btn_confirm),
            textCancelButton = getString(R.string.dialog_btn_cancel),
            onClickConfirm = onClickConfirm
        )
    }
}