# TestsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiTestsResultsPost**](TestsApi.md#apiTestsResultsPost) | **POST** /api/tests/results | post test results


<a name="apiTestsResultsPost"></a>
# **apiTestsResultsPost**
> UserTestsData apiTestsResultsPost(authorization, testResults)

post test results

Post test result

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = TestsApi()
val authorization : kotlin.String = bearer tokenValue // kotlin.String | 
val testResults : TestResults =  // TestResults | 
try {
    val result : UserTestsData = apiInstance.apiTestsResultsPost(authorization, testResults)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TestsApi#apiTestsResultsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TestsApi#apiTestsResultsPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **kotlin.String**|  |
 **testResults** | [**TestResults**](TestResults.md)|  | [optional]

### Return type

[**UserTestsData**](UserTestsData.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

