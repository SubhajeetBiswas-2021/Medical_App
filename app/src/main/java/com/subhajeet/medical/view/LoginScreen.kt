package com.subhajeet.medical.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController?) {

    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = login.value,
            onValueChange = {
                login.value=it
            },
            label={Text(text = "Email")},
            modifier=Modifier.fillMaxSize()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value=it
            },
            label={Text(text = "Password")},
            modifier=Modifier.fillMaxSize()
        )


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { /*Handle login action*/},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="Login")
        }
    }


}


@Preview(showBackground = true, showSystemUi=true)  //required only for seeing the preview
@Composable
fun AddContactPreview() {
    LoginScreen(/*viewModel = null,*/ navController = null)
}