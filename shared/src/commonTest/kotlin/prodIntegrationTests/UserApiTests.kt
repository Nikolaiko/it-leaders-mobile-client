package prodIntegrationTests

import com.model.ActionResult
import com.model.dto.auth.AuthRequestDTO
import com.model.dto.auth.AuthResponseDTO
import com.model.dto.news.NewsFullDataResponseDTO
import com.model.dto.news.NewsResponseListDTO
import com.model.dto.user.UserDataDTO
import com.services.network.api.AuthApi
import com.services.network.api.NewsApi
import com.services.network.api.UserApi
import com.services.network.clients.basicAuthClient
import com.services.network.clients.buildBearerAuthClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import mocks.TestTokenStorage
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class UserApiTests {
    private val testTokenStorage = TestTokenStorage()
    private val authApi = AuthApi(prodBaseAddress, basicAuthClient)
    private val userApi = UserApi(
        prodBaseAddress,
        buildBearerAuthClient(prodBaseAddress, testTokenStorage),
    )

    @Test
    fun testGetUserData() = runTest {
        val authResponse = authApi.loginUser(AuthRequestDTO(prodTestUserEmail, prodTestUserPassword))
        assertIs<ActionResult.Success<AuthResponseDTO>>(authResponse, "Auth user error")

        testTokenStorage.updateTokens(
            accessToken = authResponse.result.accessToken,
            refreshToken = authResponse.result.refreshToken
        )

        val userDataResponse = userApi.getUserData()
        assertIs<ActionResult.Success<UserDataDTO>>(userDataResponse, "Error getting user data")
    }
}