package com.servicetitan.mviexample.core

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Config @Inject constructor() {
    val omdbApiUrl = "https://www.omdbapi.com"
    val omdbApiKey = "eff188ce"
    val omdbApiExtraDelay = 1000
}