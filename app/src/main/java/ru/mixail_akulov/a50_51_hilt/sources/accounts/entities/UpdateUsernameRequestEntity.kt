package ru.mixail_akulov.a50_51_hilt.sources.accounts.entities

/**
 * Request body for `PUT /me` HTTP-request for updating username of the
 * current logged-in user.
 *
 * JSON example:
 * ```
 * {
 *   "username": "",
 * }
 * ```
 */
data class UpdateUsernameRequestEntity(
    val username: String
)