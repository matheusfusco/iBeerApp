package com.fiap.matheusfusco.matheusfusco.webservice.user

import retrofit2.Call
import retrofit2.http.*

interface UserAPI {

    @Headers("Accept:application/json", "Content-Type:application/json")
    @GET("api/usuarios/{login}/{senha}")
    fun login(@Path("login") login: String, @Path("senha") senha: String): Call<UserResponse>

    @Headers("Accept:application/json", "Content-Type:application/json")
    @POST("api/usuarios")
    fun register(@Body request: UserResponse): Call<Void>

}