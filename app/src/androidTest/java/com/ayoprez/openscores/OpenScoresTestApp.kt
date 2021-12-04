package com.ayoprez.openscores

class OpenScoresTestApp : OpenScoresApp() {

        var url = "http://127.0.0.1:8080"

        override fun getBaseBasketballUrl(): String {
            return url
        }

}