package com.ayoprez.openscores

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class OpenScoresApp: MultiDexApplication() {
    open fun getBaseBasketballUrl() = "https://www.balldontlie.io/api/v1/"
}