package com.dag.myapplication.ui.features.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dag.myapplication.ui.features.home.data.HomeData
import com.dag.myapplication.ui.theme.BlockMobileTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import io.metamask.androidsdk.Ethereum

@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: HomeVM = viewModel()
) {
    val context = LocalContext.current
    val ethereum = Ethereum(context)

    val homeButtons:List<HomeData> = listOf(
        HomeData({ viewModel.connectWallet(ethereum) },"Connect to Metamask"),
        HomeData({ viewModel.balance(ethereum) },"Get Balance"),
        HomeData({ viewModel.signMessage(ethereum) },"Sign Message"),
        HomeData({ viewModel.sendTransaction(ethereum) },"Send Transaction"),
        HomeData({ viewModel.switchChain(ethereum) },"Switch Chain")
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                homeButtons.forEach { button->
                    HomeMetamaskButton(onClick = { button.function() }, buttonText = button.text)
                }
            }

        }
    }
}

@Composable
fun HomeMetamaskButton(
    onClick: ()->Unit,
    buttonText: String
){
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(fraction = 0.6f)
    ) {
        Text(text = buttonText)
    }
}


@Preview
@Composable
fun HomeViewPreview(){
    BlockMobileTheme {
        HomeView(
            rememberNavController()
        )
    }
}