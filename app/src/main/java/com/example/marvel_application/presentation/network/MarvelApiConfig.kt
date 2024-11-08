package com.example.marvel_application.presentation.network

import java.security.MessageDigest

object MarvelConfig {
    const val PUBLIC_KEY = "e17edd36e9933c20a5f87cfe35db5793"
    private const val PRIVATE_KEY = "af795ed448892288de7e4c221019601efe86b57c"
    var ts: Long = System.currentTimeMillis() / 1000

    fun getHash(): String {
        return generateHash(ts, PUBLIC_KEY, PRIVATE_KEY)
    }
}

fun generateHash(ts: Long, publicKey: String, privateKey: String): String {
    val input = "$ts$privateKey$publicKey"
    return MessageDigest
        .getInstance("MD5")
        .digest(input.toByteArray())
        .joinToString("") { "%02x".format(it) }
}