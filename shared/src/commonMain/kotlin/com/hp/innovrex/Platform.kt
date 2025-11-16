package com.hp.innovrex

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform