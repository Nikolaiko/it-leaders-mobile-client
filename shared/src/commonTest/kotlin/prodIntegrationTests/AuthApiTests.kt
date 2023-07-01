package prodIntegrationTests

import com.model.ActionResult
import com.model.dto.auth.AuthRequestDTO
import com.model.dto.auth.AuthResponseDTO
import com.model.network.NetworkError
import com.services.network.api.AuthApi
import com.services.network.clients.basicAuthClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class AuthApiTests {

    private val authApi = AuthApi(prodBaseAddress, basicAuthClient)

    @Test
    fun emailAuthTesting() = runTest {
        val response = authApi.loginUser(AuthRequestDTO(prodTestUserEmail, prodTestUserPassword))
        var failedReason: NetworkError? = null
        val result: AuthResponseDTO? = when(response) {
            is ActionResult.Success -> response.result
            is ActionResult.Fail -> {
                failedReason = response.failure
                null
            }
        }
        assertIs<ActionResult.Success<AuthResponseDTO>>(
            response,
            "Auth request failed : $failedReason"
        )
    }

    //TODO vkAuthTesting
    //TODO logoutTest
}