package ru.mixail_akulov.a50_51_hilt.sources.boxes

import kotlinx.coroutines.delay
import ru.mixail_akulov.a50_51_hilt.app.model.boxes.BoxesSource
import ru.mixail_akulov.a50_51_hilt.app.model.boxes.entities.BoxAndSettings
import ru.mixail_akulov.a50_51_hilt.app.model.boxes.entities.BoxesFilter
import ru.mixail_akulov.a50_51_hilt.sources.base.BaseRetrofitSource
import ru.mixail_akulov.a50_51_hilt.sources.base.RetrofitConfig
import ru.mixail_akulov.a50_51_hilt.sources.boxes.entities.UpdateBoxRequestEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitBoxesSource @Inject constructor(
    config: RetrofitConfig
) : BaseRetrofitSource(config), BoxesSource {

    private val boxesApi = retrofit.create(BoxesApi::class.java)

    override suspend fun getBoxes(
        boxesFilter: BoxesFilter
    ): List<BoxAndSettings> = wrapRetrofitExceptions {
        delay(500)
        val isActive: Boolean? = if (boxesFilter == BoxesFilter.ONLY_ACTIVE)
            true
        else
            null

        boxesApi.getBoxes(isActive).map { it.toBoxAndSettings() }
    }

    override suspend fun setIsActive(
        boxId: Long,
        isActive: Boolean
    ) = wrapRetrofitExceptions {
        val updateBoxRequestEntity = UpdateBoxRequestEntity(isActive)
        boxesApi.setIsActive(boxId, updateBoxRequestEntity)
    }

}