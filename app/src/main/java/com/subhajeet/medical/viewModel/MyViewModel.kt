package com.subhajeet.medical.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.medical.Utils.ResultState
import com.subhajeet.medical.models.responseModels.LoginResponse
import com.subhajeet.medical.models.responseModels.getUserByIdResponse
import com.subhajeet.medical.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo): ViewModel() {

    private val _loginstate = MutableStateFlow(LoginState())
    val loginState = _loginstate.asStateFlow()

    private val _getUserByIdstate = MutableStateFlow(getUserByIdState())
    val getUserByIdstate = _getUserByIdstate.asStateFlow()


    fun login(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.login(email,password).collect{ result->
                when(result){
                    is ResultState.Loading ->{
                        _loginstate.value = LoginState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _loginstate.value = LoginState(success = result.data)
                    }
                    is ResultState.Error -> {
                        _loginstate.value = LoginState(error = result.message)
                    }
                }

            }
        }
    }


    fun getUserById(userId:String){
            viewModelScope.launch(Dispatchers.IO){
                repo.getUserById(userId).collect{ result ->
                    println("Result: $result") // Add this line
                    when(result){
                        is ResultState.Loading ->{
                            _getUserByIdstate.value = getUserByIdState(isLoading = true)
                        }
                        is ResultState.Success -> {
                            _getUserByIdstate.value = getUserByIdState(success = result.data)
                        }
                        is ResultState.Error -> {
                            _getUserByIdstate.value = getUserByIdState(error = result.message)
                        }
                    }

                }
            }
    }



}

data class LoginState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:LoginResponse? = null
)


data class  getUserByIdState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:getUserByIdResponse? = null
)

