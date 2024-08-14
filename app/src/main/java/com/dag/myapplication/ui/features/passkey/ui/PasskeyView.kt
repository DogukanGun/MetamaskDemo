package com.dag.myapplication.ui.features.passkey.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dag.myapplication.ui.features.home.ui.HomeMetamaskButton
import com.dag.myapplication.ui.theme.BlockMobileTheme

@Composable
fun PasskeyView(
    viewModel: PasskeyVM = viewModel()
){
    val context = LocalContext.current
    val credentialManager = CredentialManager.create(context)

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(vertical = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Your result is :")
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeMetamaskButton(
                    buttonText = "Register",
                    onClick = {
                        viewModel.createPasskey(
                            credentialManager,
                            context,
                        )
                    }
                )
                HomeMetamaskButton(
                    buttonText = "Login",
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun PasskeyViewPreview(){
    BlockMobileTheme {
        PasskeyView()
    }
}