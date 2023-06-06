package com.techworkscc.data.news

class Consts {
    val ApiKey = "603c059f38984dabaeed636bb14283f3" // this will be ofuscated in production, there are better options but not for a test
    enum class FlavorSourceEnum(val source: String) {
        BBC("bbc-news"),
        ABC("abc-news"),
        NFL("nfl-news")
    }

}