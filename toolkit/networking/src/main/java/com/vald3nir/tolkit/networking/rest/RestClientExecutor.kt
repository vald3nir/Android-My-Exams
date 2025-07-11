package com.vald3nir.tolkit.networking.rest

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

data class ErrorDTO(val message: String)

const val DEFAULT_ERROR_MESSAGE = "Ocorreu um erro inesperado. Tente novamente mais tarde."

open class RestClientExecutor {

    companion object {
        private val gson = Gson()
    }

    suspend inline fun <reified T> execute(call: suspend () -> T): T {
        try {
            return call()
        } catch (ex: HttpException) {
            val statusCode = ex.code()
            val apiMessage = parseErrorBody(ex.response()?.errorBody())
            throw Exception(mapToFriendlyMessage(statusCode, apiMessage))
        } catch (ex: IOException) {
            // Falha de conexão, timeout, etc.
            throw Exception("Problema de conexão. Verifique sua internet e tente novamente.")
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw Exception(DEFAULT_ERROR_MESSAGE)
        }
    }

    fun parseErrorBody(errorBody: ResponseBody?): String? {
        return try {
            errorBody?.charStream()?.use {
                gson.fromJson(it, ErrorDTO::class.java)
            }?.message
        } catch (e: Exception) {
            null
        }
    }

    fun mapToFriendlyMessage(statusCode: Int, apiMessage: String?): String {
        return when (statusCode) {
            400 -> apiMessage ?: "Requisição inválida. Verifique os dados enviados."
            401 -> "Sessão expirada. Faça login novamente."
            403 -> "Você não tem permissão para realizar esta ação."
            404 -> "Recurso não encontrado."
            500 -> "Erro interno no servidor. Tente novamente mais tarde."
            else -> apiMessage ?: DEFAULT_ERROR_MESSAGE
        }
    }
}
