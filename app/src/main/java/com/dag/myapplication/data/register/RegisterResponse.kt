package com.dag.myapplication.data.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("access_token")
    var accessToken:String = "",
    @SerializedName("token_type")
    var tokenType:String = ""
)
