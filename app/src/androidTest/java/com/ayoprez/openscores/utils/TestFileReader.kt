package com.ayoprez.openscores.utils

import androidx.test.platform.app.InstrumentationRegistry
import com.ayoprez.openscores.OpenScoresTestApp
import java.io.IOException
import java.io.InputStreamReader

class TestFileReader {
    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream =
                (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as OpenScoresTestApp).assets.open(
                    fileName
                )
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}