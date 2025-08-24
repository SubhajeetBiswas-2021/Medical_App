package com.subhajeet.medical.prf

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("user_preference")
class UserPreferences(private val context : Context) {


    companion object UserPref{
        //We will create keys here ,we can make multiple keys also depending on requirement
        private val USER_ID = stringPreferencesKey("user_id") //since we want to store string so stringPreferencesKey  name changes depending on type we want to store

    }

    suspend fun saveUserId(userId:String){
        //It will be such function that will store the the user_id
        context.dataStore.edit {
            it[USER_ID] = userId
        }
    }

    val getUserId : Flow<String?> = context.dataStore.data.map {
        it[USER_ID]
    }

    suspend fun clearUserId(){
        context.dataStore.edit { preferences:MutablePreferences ->
            preferences.remove(USER_ID)
        }
    }

    suspend fun  clearAllData(){
        context.dataStore.edit {
            it.clear()
        }
    }
}