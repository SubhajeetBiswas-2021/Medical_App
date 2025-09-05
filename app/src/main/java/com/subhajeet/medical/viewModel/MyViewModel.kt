package com.subhajeet.medical.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhajeet.medical.Utils.ResultState
import com.subhajeet.medical.models.responseModels.CreateOrderResponse
import com.subhajeet.medical.models.responseModels.CreateUserResponse
import com.subhajeet.medical.models.responseModels.LoginResponse
import com.subhajeet.medical.models.responseModels.getAllProductResponse
import com.subhajeet.medical.models.responseModels.getOrderDetailsByIdResponse
import com.subhajeet.medical.models.responseModels.getProductByProductIdResponse
import com.subhajeet.medical.models.responseModels.getUserByIdResponse
import com.subhajeet.medical.prf.UserPreferences
import com.subhajeet.medical.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: Repo, val userPreferences: UserPreferences): ViewModel() {

    private val _loginstate = MutableStateFlow(LoginState())
    val loginState = _loginstate.asStateFlow()

    private val _getUserByIdstate = MutableStateFlow(getUserByIdState())
    val getUserByIdstate = _getUserByIdstate.asStateFlow()

    private val _getAllProductstate = MutableStateFlow(getAllProductState())
    val getAllProductstate = _getAllProductstate.asStateFlow()

    private val _getProductByIdstate = MutableStateFlow(getProductByIdState())
    val getProductByIdstate = _getProductByIdstate.asStateFlow()

    private val _addOrderDetailsstate = MutableStateFlow(addOrderDetailsState())
    val addOrderDetailsstate = _addOrderDetailsstate.asStateFlow()

    private val _getOrderDetailsByIdstate = MutableStateFlow(getOrderDetailsByIdState())
    val getOrderDetailsByIdstate = _getOrderDetailsByIdstate.asStateFlow()

    private val _signUpstate = MutableStateFlow(signUpState())
    val signUpstate = _signUpstate.asStateFlow()

    fun login(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.login(email,password).collect{ result->
                when(result){
                    is ResultState.Loading ->{
                        _loginstate.value = LoginState(isLoading = true)
                    }
                    is ResultState.Success -> {
                       /* _loginstate.value = LoginState(success = result.data)
                        //Save user Id to preferences
                        userPreferences.saveUserId(result.data.userId)*/
                        val response = result.data
                        if (response.status == 200 && response.userId.isNotEmpty()) {
                            // ✅ Valid login
                            _loginstate.value = LoginState(success = response)
                            userPreferences.saveUserId(response.userId)
                        } else {
                            // ❌ Invalid credentials
                            _loginstate.value = LoginState(error = "Invalid email or password/SignUp if not ")
                        }
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

    fun getAllProduct(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getAllProduct().collect{result ->

                when(result){
                    is ResultState.Loading ->{
                        _getAllProductstate.value = getAllProductState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _getAllProductstate.value = getAllProductState(success = result.data)
                    }
                    is ResultState.Error -> {
                        _getAllProductstate.value = getAllProductState(error = result.message)
                    }
                }
            }

        }
    }


    fun getProductById(productId:String){
        viewModelScope.launch(Dispatchers.IO){
            repo.getProductById(productId).collect{ result ->
                println("Result: $result") // Add this line
                when(result){
                    is ResultState.Loading ->{
                        _getProductByIdstate.value = getProductByIdState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _getProductByIdstate.value = getProductByIdState(success = result.data)
                    }
                    is ResultState.Error -> {
                        _getProductByIdstate.value = getProductByIdState(error = result.message)
                    }
                }

            }
        }
    }

    fun addOrderDetails(user_id:String,productId:String,Quantity:String,price:String,productName:String,message:String,category:String){
        viewModelScope.launch(Dispatchers.IO){
            repo.addOrderDetails(user_id,productId,Quantity,price,productName,message,category).collect{ result ->
                println("Result: $result") // Add this line
                when(result){
                    is ResultState.Loading ->{
                        _addOrderDetailsstate.value = addOrderDetailsState(isLoading = true)
                    }
                    is ResultState.Success -> {
                        _addOrderDetailsstate.value = addOrderDetailsState(success = result.data)
                    }
                    is ResultState.Error -> {
                        _addOrderDetailsstate.value = addOrderDetailsState(error = result.message)
                    }
                }

            }
        }

    }

    fun getOrderDetailsById(userId:String){
        viewModelScope.launch(Dispatchers.IO){
            repo.getOrderDetailsById(userId).collect{ result ->
                println("Result: $result") // Add this line
                when(result){
                    is ResultState.Loading ->{
                        _getOrderDetailsByIdstate.value = getOrderDetailsByIdState(isLoading = true)
                    }
                    is ResultState.Success -> {

                        _getOrderDetailsByIdstate.value = getOrderDetailsByIdState(success = result.data)
                    }
                    is ResultState.Error -> {
                        _getOrderDetailsByIdstate.value = getOrderDetailsByIdState(error = result.message)
                    }
                }

            }
        }
    }


    fun signUp(name:String,email:String,password:String,phonenumber:String,PinCode:String,address:String){
        viewModelScope.launch(Dispatchers.IO) {
            repo.signUp(name,email,password,phonenumber,PinCode,address).collect{ result->
                when(result){
                    is ResultState.Loading ->{
                        _signUpstate.value = signUpState(isLoading = true)
                    }
                    is ResultState.Success -> {
                       // _signUpstate.value = signUp(success = result.data)

                        val response = result.data
                        if (response.status == 200 && response.message.isNotEmpty()) {
                            // ✅ Valid login
                            _signUpstate.value = signUpState(success = response)

                        } else {
                            //  Invalid credentials
                            _signUpstate.value = signUpState(error = "Invalid credential or password/SignUp if not ")
                        }
                    }
                    is ResultState.Error -> {
                        _signUpstate.value = signUpState(error = result.message)
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

data class  getAllProductState(
    val isLoading: Boolean=false,
    val error:String?=null,
    val success: getAllProductResponse?=null
)

data class getProductByIdState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:getProductByProductIdResponse? = null
)

data class addOrderDetailsState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:CreateOrderResponse? = null
)

data class getOrderDetailsByIdState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:getOrderDetailsByIdResponse? = null
)

data class signUpState(
    val isLoading:Boolean=false,
    val error:String?=null,
    val success:CreateUserResponse? = null
)

