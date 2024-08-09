package com.dag.myapplication.home.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.metamask.androidsdk.Dapp
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumMethod
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.RequestError
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class HomeVM @Inject constructor(): ViewModel() {

    var functionsResponse = MutableStateFlow("")

    fun signMessage() {

    }

    fun connectWallet(ethereum:Ethereum) {
        val dapp = Dapp("Metamask Test", "https://www.dogukangun.de")

        ethereum.connect(dapp) { result ->
            if (result is RequestError) {
                Log.e(Companion.TAG, "Ethereum connection error: ${result.message}")
                functionsResponse.value = "Connection Error"
            } else {
                Log.d(Companion.TAG, "Ethereum connection result: $result")
                functionsResponse.value = "Connection Done"
            }
        }
    }

    fun sendTransaction() {

    }

    fun switchChain() {

    }

    fun balance(ethereum:Ethereum) {

        val params: List<String> = listOf(
            ethereum.selectedAddress,
            "latest"
        )

        val getBalanceRequest = EthereumRequest(
            method = EthereumMethod.ETH_GET_BALANCE.value,
            params = params
        )

        ethereum.sendRequest(getBalanceRequest) { result ->
            if (result is RequestError) {
                // Handle error.
            } else {
                result?.let { functionsResponse.value = it.toString() }
            }
        }
    }

    companion object {
        const val TAG = "HOME_VM"
    }
}