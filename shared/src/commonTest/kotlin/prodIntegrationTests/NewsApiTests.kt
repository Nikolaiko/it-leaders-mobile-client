package prodIntegrationTests

import com.model.ActionResult
import com.model.dto.auth.AuthRequestDTO
import com.model.dto.auth.AuthResponseDTO
import com.model.dto.news.NewsFullDataResponseDTO
import com.model.dto.news.NewsResponseListDTO
import com.model.network.NetworkError
import com.services.network.api.AuthApi
import com.services.network.api.NewsApi
import com.services.network.clients.basicAuthClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class NewsApiTests {

    private val newsApi = NewsApi(prodBaseAddress, basicAuthClient)

    @Test
    fun testNewsByCategory() = runTest {
        for (currentCategory in newsCategoryNames) {
            val response = newsApi.getNewsByCategory(categoryName = currentCategory)
            assertIs<ActionResult.Success<NewsResponseListDTO>>(
                response,
                message = "News request failed for category: $currentCategory"
            )
        }
    }

    @Test
    fun testNewsById() = runTest {
        val response = newsApi.getNewsByCategory(categoryName = newsCategoryNames.first())
        assertIs<ActionResult.Success<NewsResponseListDTO>>(response, message = "News request failed")

        val newsList = response.result
        assertTrue(newsList.news.isNotEmpty(), message = "News list empty")

        val currentNews = newsList.news.first()
        val newsByIdResponse = newsApi.getNewsById(currentNews.id!!)
        assertIs<ActionResult.Success<NewsFullDataResponseDTO>>(
            newsByIdResponse,
            message = "News request failed for category: ${newsCategoryNames.first()} and id: ${currentNews.id}"
        )
    }
}