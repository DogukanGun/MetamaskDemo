package com.dag.myapplication.services

import NetworkResponseAdapter
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)){
            return null
        }
        check(returnType is ParameterizedType){
            "return type must be parameterized as Call<BaseResult<<Foo>> or Call<BaseResult<out Foo>>"
        }

        val responseType = getParameterUpperBound(0,returnType)

        check(responseType is ParameterizedType){"Response must be parameterized as BaseResult<Foo> or BaseResult<out Foo>"}

        val successBodyType = getParameterUpperBound(0,responseType)
        return NetworkResponseAdapter<Any>(successBodyType)
    }
}