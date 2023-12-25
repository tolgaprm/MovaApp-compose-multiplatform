package feature_person_detail.data.remote.service

import feature_person_detail.data.remote.dto.PersonDetailDto

interface PersonService {

    suspend fun getPersonDetail(
        id: Int,
        language: String
    ): PersonDetailDto
}