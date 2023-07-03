package mocks

import com.services.storage.TokenStorage

class TestTokenStorage: TokenStorage {
    private var currentAccessToken: String = ""
    private var currentRefreshToken: String = ""


    override fun updateTokens(accessToken: String, refreshToken: String) {
        currentAccessToken = accessToken
        currentRefreshToken = refreshToken
    }

    override fun getLastTokens(): Pair<String, String> {
        return Pair(currentAccessToken, currentRefreshToken)
    }
}