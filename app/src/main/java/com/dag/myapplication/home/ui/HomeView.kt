package com.dag.myapplication.home.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dag.myapplication.home.data.HomeData
import com.dag.myapplication.ui.theme.BlockMobileTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeView(
    navController: NavHostController,
    viewModel: HomeVM = viewModel()
) {

    val homeButtons:List<HomeData> = listOf(
        HomeData(viewModel.connectWallet(),"Connect to Metamask"),
        HomeData(viewModel.balance(),"Get Balance"),
        HomeData(viewModel.signMessage(),"Sign Message"),
        HomeData(viewModel.balance(),"Send Transaction"),
        HomeData(viewModel.balance(),"Switch Chain")
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
                    HomeMetamaskButton(onClick = { button.function }, buttonText = button.text)
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