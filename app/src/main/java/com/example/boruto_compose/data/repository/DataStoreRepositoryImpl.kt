package com.example.boruto_compose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.boruto_compose.domain.repository.DataStoreRepository
import com.example.boruto_compose.util.Constant.ON_BOARDING_PREFERENCES_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore : DataStore<Preferences>
) : DataStoreRepository {

    object PreferencesKey{
        val onBoardingKey = booleanPreferencesKey(ON_BOARDING_PREFERENCES_KEY)
    }

    override suspend fun writeToDataStore(completed: Boolean) {
        withContext(Dispatchers.IO){
            dataStore.edit { pref ->
                pref[PreferencesKey.onBoardingKey] = completed
            }
        }
    }

    override fun readFromDataStore(): Flow<Boolean> {
        return dataStore.data.catch { e ->
            if (e is IOException) emit(emptyPreferences())
            else throw e
        }.map { pref ->
            val completed = pref[PreferencesKey.onBoardingKey] ?: false
            completed
        }
    }
}