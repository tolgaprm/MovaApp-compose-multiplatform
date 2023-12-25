package feature_person_detail.domain.repository

import core.common.Resource
import feature_person_detail.domain.model.PersonDetail

interface PersonRepository {

    suspend fun getPersonDetail(
        id: Int,
        language: String = "en"
    ): Resource<PersonDetail>
}