package com.vald3nir.myexams.repository.usecases

import com.vald3nir.myexams.domain.dto.ExamDTO
import java.io.File
import java.text.Normalizer
import java.util.Locale
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper

internal object ExamPdfParser {

    fun parse(pdfPath: String): ExamDTO {
        val file = File(pdfPath)
        require(file.exists()) { "PDF file not found: $pdfPath" }
        require(file.isFile) { "Path must point to a file: $pdfPath" }

        val rawText = PDDocument.load(file).use { document ->
            PDFTextStripper().getText(document)
        }
        val text = normalize(rawText)

        return ExamDTO(
            date = findDate(text),
            lab = findLab(rawText),
            totalCholesterol = findIntValue(text, listOf("COLESTEROL\\s+TOTAL")),
            hdl = findIntValue(text, listOf("HDL(?:\\s+COLESTEROL)?")),
            notHdl = findIntValue(text, listOf("NAO\\s+HDL", "COLESTEROL\\s+NAO\\s+HDL")),
            ldl = findIntValue(text, listOf("LDL(?:\\s+COLESTEROL)?", "COLESTEROL\\s+LDL")),
            triglycerides = findIntValue(text, listOf("TRIGLICERIDEOS", "TRIGLICERIDES")),
            uricAcid = findDecimalValue(text, listOf("ACIDO\\s+URICO")),
        )
    }

    private fun findDate(text: String): String? {
        val labelledDateRegex = Regex(
            pattern = """(?:DATA(?:\s+DE)?\s+(?:COLETA|EXAME|EMISSAO)|COLETA)\s*[:\-]?\s*(\d{2}/\d{2}/\d{4})""",
            options = setOf(RegexOption.IGNORE_CASE),
        )
        labelledDateRegex.find(text)?.let { return it.groupValues[1] }
        return Regex("""\b\d{2}/\d{2}/\d{4}\b""").find(text)?.value
    }

    private fun findLab(originalText: String): String? {
        val regex = Regex(
            pattern = """(?:LABORAT[ÓO]RIO|LAB)\s*[:\-]?\s*([^\r\n]+)""",
            options = setOf(RegexOption.IGNORE_CASE),
        )
        return regex.find(originalText)?.groupValues?.get(1)?.trim()?.takeIf { it.isNotBlank() }
    }

    private fun findIntValue(text: String, labels: List<String>): Int? {
        return findValue(text, labels)?.toIntOrNull()
    }

    private fun findDecimalValue(text: String, labels: List<String>): Double? {
        return findValue(text, labels)?.replace(',', '.')?.toDoubleOrNull()
    }

    private fun findValue(text: String, labels: List<String>): String? {
        val labelPattern = labels.joinToString(separator = "|", prefix = "(?:", postfix = ")")
        val regex = Regex(
            pattern = """$labelPattern[^\d]{0,40}(\d{1,3}(?:[.,]\d+)?)""",
            options = setOf(RegexOption.IGNORE_CASE, RegexOption.DOT_MATCHES_ALL),
        )
        return regex.find(text)?.groupValues?.get(1)
    }

    private fun normalize(text: String): String {
        val normalized = Normalizer.normalize(text, Normalizer.Form.NFD)
            .replace(Regex("""\p{Mn}+"""), "")
        return normalized.uppercase(Locale.ROOT)
    }
}
