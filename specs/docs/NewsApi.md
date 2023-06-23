# NewsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiNewsGet**](NewsApi.md#apiNewsGet) | **GET** /api/news | get news list by category
[**apiNewsIdGet**](NewsApi.md#apiNewsIdGet) | **GET** /api/news/{id} | get news by id


<a name="apiNewsGet"></a>
# **apiNewsGet**
> NewsShortDataList apiNewsGet(category)

get news list by category

get news list by category

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = NewsApi()
val category : NewsCategory =  // NewsCategory | News category client interested in
try {
    val result : NewsShortDataList = apiInstance.apiNewsGet(category)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling NewsApi#apiNewsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling NewsApi#apiNewsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **category** | [**NewsCategory**](.md)| News category client interested in | [optional] [enum: music, art, theater, choreography]

### Return type

[**NewsShortDataList**](NewsShortDataList.md)

### Authorization


Configure basicAuth:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="apiNewsIdGet"></a>
# **apiNewsIdGet**
> NewsFullData apiNewsIdGet(id)

get news by id

get news by id

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = NewsApi()
val id : kotlin.Int = 56 // kotlin.Int | News id
try {
    val result : NewsFullData = apiInstance.apiNewsIdGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling NewsApi#apiNewsIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling NewsApi#apiNewsIdGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **kotlin.Int**| News id |

### Return type

[**NewsFullData**](NewsFullData.md)

### Authorization


Configure basicAuth:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

