package ru.mixail_akulov.a50_51_hilt.sources.accounts

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import ru.mixail_akulov.a50_51_hilt.sources.accounts.entities.*

interface AccountsApi {

    @POST("sign-in")
    suspend fun signIn(@Body body: SignInRequestEntity): SignInResponseEntity

    @POST("sign-up")
    suspend fun signUp(@Body body: SignUpRequestEntity)

    @GET("me")
    suspend fun getAccount(): GetAccountResponseEntity

    @PUT("me")
    suspend fun setUsername(@Body body: UpdateUsernameRequestEntity)

}