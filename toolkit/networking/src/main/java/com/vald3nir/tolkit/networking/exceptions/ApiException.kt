package com.vald3nir.tolkit.networking.exceptions

sealed class ApiException(message: String) : Exception(message)

class BodyNullException(message: String = "Resposta bem-sucedida, mas corpo nulo") : ApiException(message)
class NotFoundException(message: String = "Recurso não encontrado") : ApiException(message)
class UnauthorizedException(message: String = "Não autorizado") : ApiException(message)
class ForbiddenException(message: String = "Acesso negado") : ApiException(message)
class ServerErrorException(message: String = "Erro interno do servidor") : ApiException(message)
class BadRequestException(message: String = "Requisição inválida") : ApiException(message)
class UnknownApiException(code: Int, message: String) : ApiException("Erro desconhecido: $code - $message")
