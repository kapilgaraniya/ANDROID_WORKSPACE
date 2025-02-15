package com.example.m_7_fetchdata

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
