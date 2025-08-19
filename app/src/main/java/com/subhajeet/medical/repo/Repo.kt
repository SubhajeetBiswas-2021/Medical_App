package com.subhajeet.medical.repo

import com.subhajeet.medical.Utils.ResultState
import com.subhajeet.medical.models.ApiServices
import com.subhajeet.medical.models.responseModels.LoginResponse
import com.subhajeet.medical.models.responseModels.getAllProductResponse
import com.subhajeet.medical.models.responseModels.getUserByIdResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo @Inject constructor(private val apiServices: ApiServices) {




    //suspend fun login(email:String,password:String) = apiServices.login(email,password)
    suspend fun login(email:String,password:String) : Flow<ResultState<LoginResponse>> = flow{

        emit(ResultState.Loading)
        try{
            val response = apiServices.login(email,password)

            if(response.isSuccessful && response.body() != null){
                emit(ResultState.Success(response.body()!!))
            }else{
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        }catch (e:Exception){
            emit(ResultState.Error(e.message?:"An error message"))
        }
    }

    suspend fun getUserById(userId:String) : Flow<ResultState<getUserByIdResponse>> = flow{

        emit(ResultState.Loading)
        try{
            val response = apiServices.getUserById(userId)

            if(response.isSuccessful && response.body() != null){
                emit(ResultState.Success(response.body()!!))
            }else{
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        }catch (e:Exception){
            emit(ResultState.Error(e.message?:"An error message"))
        }
    }

    suspend fun getAllProduct():Flow<ResultState<getAllProductResponse>> = flow {

        emit(ResultState.Loading)
        try {
            val response = apiServices.getAllProduct()

            if (response.isSuccessful && response.body() !=null){
                emit(ResultState.Success(response.body()!!))
            }else{
                emit(ResultState.Error(response.errorBody()?.string() ?: "Unknown error"))
            }
        }catch (e:Exception){
            emit(ResultState.Error(e.message?:"An error message"))
        }
    }

}