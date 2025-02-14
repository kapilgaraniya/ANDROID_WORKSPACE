package com.example.skylineweather


class Model(

    var name: String = "",
    var main: Main = Main(),
    val weather: List<Weather> = emptyList(),
    var wind: Wind = Wind()
)


data class Main(

    var temp: Double = 0.0,
    var humidity: Int = 0
)


data class Weather(

    var description: String = "",
    var icon: String = ""
)


data class Wind(

    var speed: Double = 0.0
)