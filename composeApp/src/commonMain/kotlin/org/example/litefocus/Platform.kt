package org.example.litefocus

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform