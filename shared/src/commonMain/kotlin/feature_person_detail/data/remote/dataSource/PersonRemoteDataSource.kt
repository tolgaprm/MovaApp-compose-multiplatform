package feature_person_detail.data.remote.dataSource

import feature_person_detail.data.remote.dto.PersonDetailDto
import feature_person_detail.data.remote.service.PersonService

class PersonRemoteDataSource(
    private val personService: PersonService
) {
    suspend fun getPersonDetail(
        id: Int,
        language: String
    ): PersonDetailDto {
        return personService.getPersonDetail(id = id, language = language)
    }
}