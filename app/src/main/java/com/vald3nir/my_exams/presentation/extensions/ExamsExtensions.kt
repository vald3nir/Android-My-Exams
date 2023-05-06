package com.vald3nir.my_exams.presentation.extensions

import androidx.core.view.isVisible
import com.vald3nir.core.extensions.views.setTextColorTo
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.databinding.ItemViewExamsBinding
import com.vald3nir.my_exams.domain.validators.validateHDL
import com.vald3nir.my_exams.domain.validators.validateLDL
import com.vald3nir.my_exams.domain.validators.validateNotHDL
import com.vald3nir.my_exams.domain.validators.validateTotalCholesterol
import com.vald3nir.my_exams.domain.validators.validateTriglycerides
import com.vald3nir.my_exams.domain.validators.validateUricAcid
import com.vald3nir.core.R as UIR

fun ItemViewExamsBinding.bindItem(exam: ExamDTO, onClickExam: (ExamDTO) -> Unit) {
    btnEdit.setOnClickListener { onClickExam.invoke(exam) }
    txvDate.text = exam.date
    root.setOnClickListener {
        ivSelector.rotation *= -1
        llExams.isVisible = !llExams.isVisible
    }
    cpExamDetails.setupExamFields(exam, useEditMode = false, showDateField = false)
    txvStatus.apply {
        val alterations = exam.validateFields()
        if (alterations > 0) {
            text = String.format(resources.getString(R.string.status_altered), alterations)
            setTextColorTo(UIR.color.red)
        } else {
            text = context.getString(R.string.status_normal)
            setTextColorTo(UIR.color.green)
        }
    }
}

fun ExamDTO.validateFields(): Int {
    var numAlterations = 0
    if (!validateTotalCholesterol(totalCholesterol).passed) numAlterations++
    if (!validateHDL(HDL_D).passed) numAlterations++
    if (!validateNotHDL(NOT_HDL).passed) numAlterations++
    if (!validateLDL(LDL).passed) numAlterations++
    if (!validateTriglycerides(triglycerides).passed) numAlterations++
    if (!validateUricAcid(uricAcid).passed) numAlterations++
    return numAlterations
}