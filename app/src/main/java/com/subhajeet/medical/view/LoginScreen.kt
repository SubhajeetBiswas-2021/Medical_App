package com.subhajeet.medical.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
fun LoginScreen(navController: NavController?,viewModel: MyViewModel?= hiltViewModel()) {


    val loginState = viewModel?.loginState?.collectAsState()

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current

    if (loginState != null) {
        when{
            loginState.value.isLoading -> {

                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator()
                }
            }

            loginState.value.error != null -> {
                Toast.makeText(context,loginState.value.error,Toast.LENGTH_SHORT).show()
            }
            loginState.value.success != null -> {
                Toast.makeText(context,loginState.value.success.toString(),Toast.LENGTH_SHORT).show()
                navController?.navigate(Routes.WaitingRoutes)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value=it
            },
            label={Text(text = "Email")},
            modifier=Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value=it
            },
            label={Text(text = "Password")},
            modifier=Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { /*Handle login action*/
            viewModel?.login(email =email.value,
                password = password.value)},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="Login")
        }
    }


}


@Preview(showBackground = true, showSystemUi=true)  //required only for seeing the preview
@Composable
fun AddContactPreview() {
    LoginScreen(viewModel = null, navController = null)
}