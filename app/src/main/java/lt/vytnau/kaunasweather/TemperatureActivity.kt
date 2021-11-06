package lt.vytnau.kaunasweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import lt.vytnau.kaunasweather.databinding.TemperatureViewBinding
import lt.vytnau.kaunasweather.view.models.TemperatureViewModel

class TemperatureActivity : AppCompatActivity() {
    private val viewModel: TemperatureViewModel = TemperatureViewModel()
    private lateinit var binding: TemperatureViewBinding
    private lateinit var temperatureTextFormat: String
    private lateinit var apiToken: String
    private lateinit var city: String
    private lateinit var units: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDefaultValues()
        setContentView(R.layout.temperature_view)
        binding = TemperatureViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        textsUpdateObserver()
        setSwipeAction()
        updateData()
    }

    private fun initDefaultValues() {
        city = getString(R.string.default_city)
        units = getString(R.string.default_units)
        apiToken = getString(R.string.api_token)
        temperatureTextFormat = getString(R.string.temperature_text)
    }

    private fun setSwipeAction() {
        binding.swiperefresh.setOnRefreshListener {
            updateData()
        }
    }

    private fun textsUpdateObserver() {
        viewModel.uiTemperatureLiveData.observe(this) { temperatureModel ->
            binding.temperatureText.text = String.format(temperatureTextFormat, temperatureModel.temperature)
            binding.swiperefresh.isRefreshing = false
        }

        viewModel.uiErrorMessageId.observe(this) { messageId ->
            Toast.makeText(this@TemperatureActivity, getString(messageId), Toast.LENGTH_SHORT).show()
            binding.swiperefresh.isRefreshing = false
        }
    }

    private fun updateData() {
        viewModel.getTemperature(city, units, apiToken)
    }
}