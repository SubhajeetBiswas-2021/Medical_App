package com.subhajeet.medical.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.subhajeet.medical.view.nav.Routes
import com.subhajeet.medical.viewModel.MyViewModel

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreen(navController: NavController,viewModel: MyViewModel?= hiltViewModel()) {

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val phonenumber = remember { mutableStateOf("") }
    val PinCode = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val context = LocalContext.current

    val signUpstate = viewModel?.signUpstate?.collectAsState()

    if (signUpstate != null) {
        when{
            signUpstate.value.isLoading -> {

                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator()
                }
            }

            signUpstate.value.error != null -> {
                Toast.makeText(context,signUpstate.value.error,Toast.LENGTH_SHORT).show()
            }
            signUpstate.value.success != null -> {
                Toast.makeText(context,signUpstate.value.success.toString(),Toast.LENGTH_SHORT).show()
                //navController?.navigate(Routes.WaitingRoutes)
                if (signUpstate.value.success != null) {
                    navController.navigate(Routes.LoginRoutes)
                }

            }
        }
    }


    Column(modifier = Modifier.fillMaxSize().padding(35.dp)) {

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value=it
            },
            label={ Text(text = "Name") },
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value=it
            },
            label={ Text(text = "Email") },
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                 password.value=it
            },
            label={ Text(text = "Password") },
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phonenumber.value,
            onValueChange = {
                phonenumber.value=it
            },
            label={ Text(text = "Phone Number") },
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = PinCode.value,
            onValueChange = {
                PinCode.value=it
            },
            label={ Text(text = "PIN:") },
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = address.value,
            onValueChange = {
                address.value=it
            },
            label={ Text(text = "Address") },
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { /*Handle login action*/
                when{
                    name.value.isBlank() ->{
                        Toast.makeText(context,"Please Enter Name", Toast.LENGTH_SHORT).show()}
                    email.value.isBlank() ->{
                        Toast.makeText(context,"Please Enter Email", Toast.LENGTH_SHORT).show()}
                    password.value.isBlank() ->{
                        Toast.makeText(context,"Please Enter Password", Toast.LENGTH_SHORT).show()}
                    phonenumber.value.isBlank() ->{
                        Toast.makeText(context,"Please Enter Phone Number", Toast.LENGTH_SHORT).show()}
                    PinCode.value.isBlank() ->{
                        Toast.makeText(context,"Please Enter PinCode", Toast.LENGTH_SHORT).show()}
                    address.value.isBlank() ->{
                        Toast.makeText(context,"Please Enter Address", Toast.LENGTH_SHORT).show()}
                    else ->{
                        viewModel?.signUp(name=name.value,email =email.value,
                            password = password.value,phonenumber=phonenumber.value,PinCode=PinCode.value,address=address.value)
                    }
                }
                /*viewModel?.login(email =email.value,
                    password = password.value)*/},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="SignUp")
        }




    }

}