package com.t1.proyecto_final_ejemplo.di

import android.content.Context
import androidx.room.Room
import com.t1.proyecto_final_ejemplo.data.local.AppDatabase
import com.t1.proyecto_final_ejemplo.data.local.MascotaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pawsy_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMascotaDao(database: AppDatabase): MascotaDao {
        return database.mascotaDao()
    }
}
