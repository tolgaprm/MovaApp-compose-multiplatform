package feature_person_detail.data.repository

import core.common.Resource
import core.data.util.tryResultReturnResource
import feature_person_detail.data.remote.dataSource.PersonRemoteDataSource
import feature_person_detail.data.remote.mapper.toPersonDetail
import feature_person_detail.domain.model.PersonDetail
import feature_person_detail.domain.repository.PersonRepository

class PersonRepositoryImpl(
    private val personRemoteDataSource: PersonRemoteDataSource
) : PersonRepository {
    override suspend fun getPersonDetail(id: Int, language: String): Resource<PersonDetail> {
        return tryResultReturnResource {
            personRemoteDataSource.getPersonDetail(
                id = id,
                language = language
            ).toPersonDetail()
        }
    }
}