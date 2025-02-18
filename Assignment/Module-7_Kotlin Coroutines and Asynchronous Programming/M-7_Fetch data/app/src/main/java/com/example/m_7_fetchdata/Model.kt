package com.example.m_7_fetchdata

data class User(
    val name: Name,
    val email: String,
    val picture: Picture
)

data class Name(val first: String, val last: String)
data class Picture(val large: String)
