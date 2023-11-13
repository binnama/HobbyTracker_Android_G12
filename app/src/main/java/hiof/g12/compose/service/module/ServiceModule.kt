package hiof.g12.compose.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hiof.g12.compose.service.AccountService
import hiof.g12.compose.service.StorageService
import hiof.g12.compose.service.impl.AccountServiceImpl
import hiof.g12.compose.service.impl.StorageServiceImpl


// Denne koden ble hentet av forelesningen under modul: Firebase Authentication
@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}