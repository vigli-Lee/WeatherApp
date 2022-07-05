package com.plcoding.weatherapp.presentation

import com.plcoding.weatherapp.data.remote.WeatherApi
import com.plcoding.weatherapp.data.repository.WeatherRepositoryImpl
import com.plcoding.weatherapp.domain.FakeLocationTracker
import com.plcoding.weatherapp.domain.location.LocationTracker
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import io.kotest.core.spec.style.AnnotationSpec
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class WeatherViewModelUnitTest: AnnotationSpec() {
    private lateinit var repository: WeatherRepository
    private lateinit var locationTracker: LocationTracker

    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var weatherApi: WeatherApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        weatherApi = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()

        locationTracker = FakeLocationTracker()

        repository = WeatherRepositoryImpl(weatherApi)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()

    }
}