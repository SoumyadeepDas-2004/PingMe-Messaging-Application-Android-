//package com.soumyadeep.pingme.di
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule
//{
//    @Provides
//    @Singleton
//    fun provideFirebaseAuth():FirebaseAuth
//    {
//        return FirebaseAuth.getInstance()
//    }
//
//    @Provides
//    @Singleton
//    fun provideFirebaseDatabase(): FirebaseDatabase
//    {
//        return FirebaseDatabase.getInstance()
//    }
//}
package com.soumyadeep.pingme.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.soumyadeep.pingme.repository.ProfileRepository
import com.soumyadeep.pingme.repository.ProfileRepositoryImpl
import android.content.Context
import com.soumyadeep.pingme.model.DataStoreManager
import dagger.hilt.android.qualifiers.ApplicationContext
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Module
    @InstallIn(SingletonComponent::class)
    object RepositoryModule {

        @Provides
        @Singleton
        fun provideProfileRepository(db: FirebaseDatabase): ProfileRepository =
            ProfileRepositoryImpl(db)
    }
    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ): DataStoreManager {
        return DataStoreManager(context)
    }
}
