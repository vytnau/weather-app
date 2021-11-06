package lt.vytnau.kaunasweather.service

import android.util.Log
import com.google.gson.GsonBuilder
import lt.vytnau.kaunasweather.entity.WeatherEntity
import lt.vytnau.kaunasweather.entity.WeatherEntityDeserializer
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {
    private var baseURL = "https://api.openweathermap.org/"
    private val service: WeatherApi

    init {
        val gson =
            GsonBuilder().registerTypeAdapter(
                WeatherEntity::class.java,
                WeatherEntityDeserializer()
            ).create()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseURL)
            .build()
        service = retrofit.create()
    }


    fun getData(city: String, units: String, token: String, onSuccess: (temp: Double) -> Unit, onError: () -> Unit) {
        service.getCurrentWeather(city, units, token).enqueue(object : Callback<WeatherEntity> {
            override fun onResponse(call: Call<WeatherEntity>, response: Response<WeatherEntity>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (result?.currentTemp != null) {
                        onSuccess.invoke(result.currentTemp!!)
                    }
                } else {
                    Log.e("WeatherService", response.code().toString() + " " + response.message())
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                Log.e("WeatherService", t.message, t)
                onError.invoke()
            }
        })
    }
}