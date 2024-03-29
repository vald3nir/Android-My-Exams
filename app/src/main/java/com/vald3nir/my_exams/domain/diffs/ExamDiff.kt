package com.vald3nir.my_exams.domain.diffs

import androidx.recyclerview.widget.DiffUtil
import com.vald3nir.my_exams.data.dto.ExamDTO

fun examsDiff(): DiffUtil.ItemCallback<ExamDTO> = object : DiffUtil.ItemCallback<ExamDTO>() {

    override fun areItemsTheSame(oldItem: ExamDTO, newItem: ExamDTO): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ExamDTO, newItem: ExamDTO): Boolean {
        return oldItem == newItem
    }
}