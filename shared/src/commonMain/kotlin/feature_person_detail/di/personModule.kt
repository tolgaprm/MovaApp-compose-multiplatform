package feature_person_detail.di

import feature_person_detail.data.remote.PersonRemoteDataSource
import feature_person_detail.data.remote.service.PersonService
import feature_person_detail.data.remote.service.PersonServiceImpl
import feature_person_detail.data.repository.PersonRepositoryImpl
import feature_person_detail.domain.repository.PersonRepository
import org.koin.dsl.module

val personModule = module {
    single<PersonService> { PersonServiceImpl(get(), get()) }
    single { PersonRemoteDataSource(get()) }
    single<PersonRepository> { PersonRepositoryImpl(get()) }
}