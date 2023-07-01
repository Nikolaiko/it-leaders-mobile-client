package prodIntegrationTests

import com.model.ActionResult
import com.model.dto.auth.AuthRequestDTO
import com.model.dto.auth.AuthResponseDTO
import com.model.network.NetworkError
import com.services.network.api.AuthApi
import com.services.network.api.NewsApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class NewsApiTests {

    private val newsApi = NewsApi(prodBaseAddress)

    @Test
    fun refreshTokenTest() = runTest {
        val response = authApi.loginUser(AuthRequestDTO(prodTestUserEmail, prodTestUserPassword))
        assertIs<ActionResult.Success<AuthResponseDTO>>(
            response,
            message = "RefreshTokenTest: AuthRequestFailed"
        )

        val refreshResponse = authApi.

        var failedReason: NetworkError? = null
        val result: AuthResponseDTO? = when(response) {
            is ActionResult.Success -> response.result
            is ActionResult.Fail -> {
                failedReason = response.failure
                null
            }
        }
        assertNotNull(
            result,
            "Auth request failed : $failedReason"
        )
    }
}