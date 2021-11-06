package lt.vytnau.kaunasweather.view.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import lt.vytnau.kaunasweather.R
import lt.vytnau.kaunasweather.model.TemperatureModel
import lt.vytnau.kaunasweather.service.WeatherService

class TemperatureViewModel : ViewModel() {
    private var dataProvider = WeatherService()
    val uiTemperatureLiveData = MutableLiveData<TemperatureModel>()
    val uiErrorMessageId = MutableLiveData<Int>()

    fun getTemperature(city: String, units: String, token: String) {
        dataProvider.getData(city, units, token, onSuccess(), onError())
    }

    private fun onSuccess(): (temp: Double) -> Unit {
        return { temp -> uiTemperatureLiveData.postValue(TemperatureModel(temp)) }
    }

    private fun onError(): () -> Unit {
        return { uiErrorMessageId.postValue(R.string.on_error) }
    }
}
