package com.plcoding.weatherapp.domain

import android.location.Location
import com.plcoding.weatherapp.domain.location.LocationTracker

class FakeLocationTracker: LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        return Location("test").apply {
            latitude = 37.5
            longitude = 126.75
        }
    }
}