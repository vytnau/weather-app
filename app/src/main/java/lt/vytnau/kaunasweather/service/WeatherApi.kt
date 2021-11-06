package lt.vytnau.kaunasweather.service

import lt.vytnau.kaunasweather.entity.WeatherEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather?")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appId: String
    ): Call<WeatherEntity>
}