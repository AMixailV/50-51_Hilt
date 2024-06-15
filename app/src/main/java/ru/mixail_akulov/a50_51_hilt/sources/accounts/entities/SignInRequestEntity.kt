package ru.mixail_akulov.a50_51_hilt.sources.accounts.entities

/**
 * Request body for `POST /sign-in` HTTP-request.
 *
 * JSON example:
 * ```
 * {
 *   "email": "",
 *   "password": "",
 * }
 * ```
 */
data class SignInRequestEntity(
    val email: String,
    val password: String
)