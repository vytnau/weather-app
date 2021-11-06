package lt.vytnau.kaunasweather.entity

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class WeatherEntityDeserializer : JsonDeserializer<WeatherEntity> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): WeatherEntity {
        val weatherDataObject = json?.asJsonObject
        val temperatureObject = weatherDataObject?.get("main")?.asJsonObject
        val temperature = temperatureObject?.get("temp")?.asDouble
        return WeatherEntity(temperature)
    }

}