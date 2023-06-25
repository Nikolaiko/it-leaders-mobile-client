package com.services.storage

interface TokenStorage {
    fun updateTokens(accessToken: String, refreshToken: String)
    fun getLastTokens(): Pair<String, String>
}