package lt.vytnau.kaunasweather.entity

import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WeatherEntityDeserializerTest {
    private lateinit var testJson: String

    @BeforeEach
    fun setUp() {
        testJson = WeatherEntityDeserializerTest::class.java.getResource("/test/testData.json")!!
            .readText()
    }

    @Test
    fun testDeserializer() {
        val expectedEntity = WeatherEntity(282.55)
        val gson = GsonBuilder().registerTypeAdapter(WeatherEntity::class.java, WeatherEntityDeserializer()).create()
        val actualEntity = gson.fromJson(testJson, WeatherEntity::class.java)
        assertEquals(expectedEntity, actualEntity)
    }
}