package com.ruhlan.dynamicappicon.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created on 28 Jun 2025,02:06
 * @author Ruhlan Usubov
 */

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebase(@ApplicationContext context: Context) =
        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context)
        } else {
            RuntimeException("Failed to initialize")
        }


    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig() = Firebase.remoteConfig
}
