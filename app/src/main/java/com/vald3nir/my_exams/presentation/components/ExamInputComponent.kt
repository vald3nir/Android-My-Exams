package com.vald3nir.my_exams.presentation.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.vald3nir.core.extensions.formatString
import com.vald3nir.core.extensions.toFloatValue
import com.vald3nir.core.extensions.toFloatValueNullable
import com.vald3nir.core.extensions.toIntValue
import com.vald3nir.core.extensions.toIntValueNullable
import com.vald3nir.my_exams.R
import com.vald3nir.my_exams.data.dto.ExamDTO
import com.vald3nir.my_exams.databinding.ComponentExamInputBinding
import com.vald3nir.my_exams.domain.validators.validateHDL
import com.vald3nir.my_exams.domain.validators.validateLDL
import com.vald3nir.my_exams.domain.validators.validateNotHDL
import com.vald3nir.my_exams.domain.validators.validateTotalCholesterol
import com.vald3nir.my_exams.domain.validators.validateTriglycerides
import com.vald3nir.my_exams.domain.validators.validateUricAcid
import com.vald3nir.core.presentation.components.atoms.InputViewComponent

class ExamInputComponent : LinearLayoutCompat {

    private var currentExam: ExamDTO? = null
    private val binding by lazy {
        ComponentExamInputBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    init {
        orientation = VERTICAL
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )

    private fun InputViewComponent.initParam(
        useEditMode: Boolean,
        textValue: String?,
        hintText: String?,
        textInputType: Int = InputType.TYPE_CLASS_NUMBER,
        clickListener: (() -> Unit)? = null,
        onTextChanged: ((String) -> Unit)? = null,
    ) {
        setup(
            useEditMode = useEditMode,
            textValue = textValue,
            hintText = hintText,
            textInputType = textInputType,
            clickListener = clickListener,
            onTextChanged = onTextChanged
        )
        isVisible = useEditMode || !textValue.isNullOrEmpty()
        if (!useEditMode) {
            // Force field validation to be displayed when not in edit mode
            onTextChanged?.invoke(value())
        }
    }


    fun setupExamFields(
        exam: ExamDTO = ExamDTO(),
        useEditMode: Boolean = false,
        showDateField: Boolean = true
    ) = with(binding) {
        currentExam = exam

        edtDate.apply {
            setup(
                useEditMode = useEditMode,
                textValue = exam.date,
                hintText = context.getString(R.string.exam_date),
            )
            isVisible = showDateField
        }

        edtTotalCholesterol.initParam(
            useEditMode = useEditMode,
            textValue = exam.totalCholesterol.formatString(),
            hintText = context.getString(R.string.total_cholesterol),
            onTextChanged = {
                edtTotalCholesterol.showErrorMessage(validateTotalCholesterol(it.toIntValue()).description)
            }
        )
        edtHdl.initParam(
            useEditMode = useEditMode,
            textValue = exam.HDL_D.formatString(),
            hintText = context.getString(R.string.hdl_d),
            onTextChanged = {
                edtHdl.showErrorMessage(validateHDL(it.toIntValue()).description)
            }
        )
        edtNotHdl.initParam(
            useEditMode = useEditMode,
            textValue = exam.NOT_HDL.formatString(),
            hintText = context.getString(R.string.no_hdl),
            onTextChanged = {
                edtNotHdl.showErrorMessage(validateNotHDL(it.toIntValue()).description)
            }
        )
        edtLdl.initParam(
            useEditMode = useEditMode,
            textValue = exam.LDL.formatString(),
            hintText = context.getString(R.string.ldl),
            onTextChanged = {
                edtLdl.showErrorMessage(validateLDL(it.toIntValue()).description)
            }
        )
        edtTriglycerides.initParam(
            useEditMode = useEditMode,
            textValue = exam.triglycerides.formatString(),
            hintText = context.getString(R.string.triglycerides),
            onTextChanged = {
                edtTriglycerides.showErrorMessage(validateTriglycerides(it.toIntValue()).description)
            }
        )
        edtUricAcid.initParam(
            useEditMode = useEditMode,
            textValue = exam.uricAcid.formatString(),
            hintText = context.getString(R.string.uric_acid),
            textInputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL,
            onTextChanged = {
                edtUricAcid.showErrorMessage(validateUricAcid(it.toFloatValue()).description)
            }
        )
    }

    fun examEdited(): ExamDTO = with(binding) {
        return currentExam?.let {
            it.date = edtDate.value()
            it.totalCholesterol = edtTotalCholesterol.value().toIntValueNullable()
            it.HDL_D = edtHdl.value().toIntValueNullable()
            it.NOT_HDL = edtNotHdl.value().toIntValueNullable()
            it.LDL = edtLdl.value().toIntValueNullable()
            it.triglycerides = edtTriglycerides.value().toIntValueNullable()
            it.uricAcid = edtUricAcid.value().toFloatValueNullable()
            it
        } ?: ExamDTO()
    }
}