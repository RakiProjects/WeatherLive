package com.hfad.weatherlive

data class ForecastResponse(
        val forecasts: List<Forecast>,
        val throwable: Throwable?
)