package com.dag.myapplication.ui.features.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.metamask.androidsdk.Dapp
import io.metamask.androidsdk.ErrorType
import io.metamask.androidsdk.Ethereum
import io.metamask.androidsdk.EthereumMethod
import io.metamask.androidsdk.EthereumRequest
import io.metamask.androidsdk.Network
import io.metamask.androidsdk.RequestError
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeVM @Inject constructor(): ViewModel() {

    var functionsResponse = MutableStateFlow("")

    fun signMessage(ethereum:Ethereum) {
        val message = "Hello world."

        val from = ethereum.selectedAddress
        val params: List<String> = listOf(from, message)

        val signRequest = EthereumRequest(
            method = EthereumMethod.ETH_SIGN_TYPED_DATA_V4.value,
            params = params
        )

        ethereum.sendRequest(signRequest) { result ->
            if (result is RequestError) {
                Log.e(TAG, "Ethereum sign error: ${result.message}")
            } else {
                Log.d(TAG, "Ethereum sign result: $result")
            }
        }
    }

    fun connectWallet(ethereum:Ethereum) {
        val dapp = Dapp("Metamask Test", "https://www.dogukangun.de")

        ethereum.connect(dapp) { result ->
            if (result is RequestError) {
                Log.e(TAG, "Ethereum connection error: ${result.message}")
                functionsResponse.value = "Connection Error"
            } else {
                Log.d(TAG, "Ethereum connection result: $result")
                functionsResponse.value = "Connection Done"
            }
        }
    }

    fun sendTransaction(ethereum:Ethereum) {
        val from = ethereum.selectedAddress
        val to = "0x0000000000000000000000000000000000000000"
        val amount = "0x01"
        val params: Map<String, Any> = mapOf(
            "from" to from,
            "to" to to,
            "amount" to amount
        )

        val transactionRequest = EthereumRequest(
            method = EthereumMethod.ETH_SEND_TRANSACTION.value,
            params = listOf(params)
        )

        ethereum.sendRequest(transactionRequest) { result ->
            if (result is RequestError) {
                // Handle error.
            } else {
                Log.d(TAG, "Ethereum transaction result: $result")
            }
        }
    }

    fun switchChain(ethereum:Ethereum) {
        val chainID = "0x01"
        val switchChainParams: Map<String, String> = mapOf("chainId" to chainID)
        val switchChainRequest = EthereumRequest(
            method = EthereumMethod.SWITCH_ETHEREUM_CHAIN.value,
            params = listOf(switchChainParams)
        )

        ethereum.sendRequest(switchChainRequest) { result ->
            if (result is RequestError) {
                if (result.code == ErrorType.UNRECOGNIZED_CHAIN_ID.code || result.code == ErrorType.SERVER_ERROR.code) {
                    functionsResponse.value = Network.chainNameFor(chainID) +
                            " ($chainID) has not been added to your MetaMask wallet. Add chain?"
                    addEthereumChain(
                        chainID,
                        ethereum
                    )
                } else {
                    Log.e("SIGN","Switch chain error: ${result.message}")
                }
            } else {
                Log.d("SIGN","Successfully switched to ${Network.chainNameFor(chainID)}")
            }
        }
    }


    private fun addEthereumChain(
        chainId: String,
        ethereum:Ethereum
    ) {
        Log.d("SIGN","Adding chainId: $chainId")

        val addChainParams: Map<String, Any> = mapOf(
            "chainId" to chainId,
            "chainName" to Network.chainNameFor(chainId),
            "rpcUrls" to Network.rpcUrls(Network.fromChainId(chainId))
        )
        val addChainRequest = EthereumRequest(
            method = EthereumMethod.ADD_ETHEREUM_CHAIN.value,
            params = listOf(addChainParams)
        )

        ethereum.sendRequest(addChainRequest) { result ->
            if (result is RequestError) {
                Log.e("SIGN","Add chain error: ${result.message}")
            } else {
                if (chainId == ethereum.chainId) {
                    Log.d("SIGN","Successfully switched to ${Network.chainNameFor(chainId)} ($chainId)")
                } else {
                    Log.d("SIGN","Successfully added ${Network.chainNameFor(chainId)} ($chainId)")
                }
            }
        }
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