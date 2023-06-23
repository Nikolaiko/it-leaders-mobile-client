# UserApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiUserInterestsPost**](UserApi.md#apiUserInterestsPost) | **POST** /api/user/interests | set user interests
[**apiUserProfileGet**](UserApi.md#apiUserProfileGet) | **GET** /api/user/profile | get user data
[**apiUserTestsGet**](UserApi.md#apiUserTestsGet) | **GET** /api/user/tests | set user interests


<a name="apiUserInterestsPost"></a>
# **apiUserInterestsPost**
> UserData apiUserInterestsPost(authorization, userInterests)

set user interests

Change user interests

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
val authorization : kotlin.String = bearer tokenValue // kotlin.String | 
val userInterests : UserInterests =  // UserInterests | New array of user interests
try {
    val result : UserData = apiInstance.apiUserInterestsPost(authorization, userInterests)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#apiUserInterestsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#apiUserInterestsPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **kotlin.String**|  |
 **userInterests** | [**UserInterests**](UserInterests.md)| New array of user interests | [optional]

### Return type

[**UserData**](UserData.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="apiUserProfileGet"></a>
# **apiUserProfileGet**
> UserData apiUserProfileGet(authorization)

get user data

Get user data needs auth

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
val authorization : kotlin.String = bearer tokenValue // kotlin.String | 
try {
    val result : UserData = apiInstance.apiUserProfileGet(authorization)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#apiUserProfileGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#apiUserProfileGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **kotlin.String**|  |

### Return type

[**UserData**](UserData.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="apiUserTestsGet"></a>
# **apiUserTestsGet**
> UserTestsData apiUserTestsGet(authorization)

set user interests

Change user interests

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = UserApi()
val authorization : kotlin.String = bearer tokenValue // kotlin.String | 
try {
    val result : UserTestsData = apiInstance.apiUserTestsGet(authorization)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UserApi#apiUserTestsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UserApi#apiUserTestsGet")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authorization** | **kotlin.String**|  |

### Return type

[**UserTestsData**](UserTestsData.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

