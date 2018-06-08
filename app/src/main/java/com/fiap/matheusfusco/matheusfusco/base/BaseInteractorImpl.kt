package com.fiap.matheusfusco.matheusfusco.base

import com.fiap.matheusfusco.matheusfusco.webservice.Deserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseInteractorImpl {

    private var retrofit: Retrofit? = null

    fun createAPI(domain: String, apiClass: Class<*>): Any {
        val gson = GsonBuilder().registerTypeAdapter(Deserializer::class.java, Deserializer()).create()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(httpLoggingInterceptor)

        retrofit = Retrofit.Builder().baseUrl(domain).client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

        return retrofit!!.create(apiClass)

    }

    fun convetJsonToObjct(json: String, clazz: Class<*>): Any? {

        if (json == null)
            return null

        val gson = Gson()
        val fromJson = gson.fromJson(json, clazz)

        return fromJson
    }
}