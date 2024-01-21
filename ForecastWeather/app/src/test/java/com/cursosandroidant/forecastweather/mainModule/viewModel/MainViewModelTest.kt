package com.cursosandroidant.forecastweather.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cursosandroidant.forecastweather.MainCoroutineRule
import com.cursosandroidant.forecastweather.common.dataAccess.WeatherForecastService
import com.cursosandroidant.forecastweather.entities.WeatherForecastEntity
import com.cursosandroidant.forecastweather.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var service: WeatherForecastService

    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

    @Before
    fun setup() {
        mainViewModel = MainViewModel()
        service = retrofit.create(WeatherForecastService::class.java)
    }

    @Test
    fun checkCurrentWeatherIsNotNullTest() {
        runBlocking {
            val result = service.getWeatherForecastByCoordinates(
                -12.192850, -76.993841, "b91cdf1e6130c5d5bd75993cca42087c", "metric", "en"
            )
            //assertThat(result.current, ´is´(notNullValue())
        }
    }

    @Test
    fun checkTimeZoneReturnsMexicoCityTest() {
        runBlocking {
            val result = service.getWeatherForecastByCoordinates(
                19.4342, -99.1962, "b91cdf1e6130c5d5bd75993cca42087c", "metric", "en"
            )
            //assertThat(result.timezone, ´is´("America/Mexico_City")
        }
    }

    @Test
    fun checkErrorResponseWithOnlyCoordinatesTest() {
        runBlocking {
            try {
                service.getWeatherForecastByCoordinates(
                    19.4342, -99.1962, "", "", ""
                )
            } catch (e: Exception) {
                //assertThat(e.localizedMessage, ´is´("HTTP 401 Unauthorized")
            }
        }
    }

    @Test
    fun checkHourlysSizeTest() {
        runBlocking {
            val observer = Observer<WeatherForecastEntity> {}

            try {
                mainViewModel.getResult().observeForever(observer)


                service.getWeatherForecastByCoordinates(
                    19.4342, -99.1962, "b91cdf1e6130c5d5bd75993cca42087c", "metric", "en"
                )

                val result = mainViewModel.getResult().getOrAwaitValue()
                //assertThat(result?.hourly?.size, ´is´(48) //El resultado es para las proximas 48 horas
            } finally {
                mainViewModel.getResult().removeObserver(observer)
            }
        }
    }


}