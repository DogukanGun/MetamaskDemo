package com.dag.myapplication.ui.features.passkey.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialCustomException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.CreateCredentialInterruptedException
import androidx.credentials.exceptions.CreateCredentialProviderConfigurationException
import androidx.credentials.exceptions.CreateCredentialUnknownException
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dag.myapplication.data.register.RegisterRequest
import com.dag.myapplication.services.ApiService
import com.dag.myapplication.services.ApiSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

@HiltViewModel
class PasskeyVM @Inject constructor(
    private val apiSource: ApiSource
): ViewModel() {
    @SuppressLint("PublicKeyCredential")
    fun createPasskey(credentialManager: CredentialManager,
                              context:Context
    ) {

        viewModelScope.launch {
                apiSource.register(RegisterRequest("testforpass1","test"))
                    .collect{
                        println(it)
                    }
            try {
                val result = credentialManager.createCredential(
                    request = CreatePasswordRequest("test","test"),
                    context = context
                )

                Log.d("PASSKEY",result.toString())
                //handlePasskeyRegistrationResult(result)
            } catch (e : CreateCredentialException){
                handleFailure(e)
            }
        }
    }

    fun handleFailure(e: CreateCredentialException) {
        when (e) {
            is CreatePublicKeyCredentialDomException -> {
                // Handle the passkey DOM errors thrown according to the
                // WebAuthn spec.
            }
            is CreateCredentialCancellationException -> {
                // The user intentionally canceled the operation and chose not
                // to register the credential.
            }
            is CreateCredentialInterruptedException -> {
                // Retry-able error. Consider retrying the call.
            }
            is CreateCredentialProviderConfigurationException -> {
                // Your app is missing the provider configuration dependency.
                // Most likely, you're missing the
                // "credentials-play-services-auth" module.
            }
            is CreateCredentialUnknownException -> {

            }
            is CreateCredentialCustomException -> {
                // You have encountered an error from a 3rd-party SDK. If you
                // make the API call with a request object that's a subclass of
                // CreateCustomCredentialRequest using a 3rd-party SDK, then you
                // should check for any custom exception type constants within
                // that SDK to match with e.type. Otherwise, drop or log the
                // exception.
            }
            else -> Log.w("Passkey", "Unexpected exception type ${e::class.java.name}")
        }
    }
}