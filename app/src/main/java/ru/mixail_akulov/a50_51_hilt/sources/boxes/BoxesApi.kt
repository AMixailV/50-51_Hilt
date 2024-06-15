package ru.mixail_akulov.a50_51_hilt.sources.boxes

import retrofit2.http.*
import ru.mixail_akulov.a50_51_hilt.sources.boxes.entities.GetBoxResponseEntity
import ru.mixail_akulov.a50_51_hilt.sources.boxes.entities.UpdateBoxRequestEntity

interface BoxesApi {

    @GET("boxes")
    suspend fun getBoxes(@Query("active") isActive: Boolean?): List<GetBoxResponseEntity>

    @PUT("boxes/{boxId}")
    suspend fun setIsActive(
        @Path("boxId") boxId: Long,
        @Body body: UpdateBoxRequestEntity
    )

}