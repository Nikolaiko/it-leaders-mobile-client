# AuthApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiAuthLoginEmailPost**](AuthApi.md#apiAuthLoginEmailPost) | **POST** /api/auth/login/email | login user
[**apiAuthLoginVkPost**](AuthApi.md#apiAuthLoginVkPost) | **POST** /api/auth/login/vk | login user
[**apiAuthRefreshPost**](AuthApi.md#apiAuthRefreshPost) | **POST** /api/auth/refresh | register new user
[**apiAuthRegisterPost**](AuthApi.md#apiAuthRegisterPost) | **POST** /api/auth/register | register new user


<a name="apiAuthLoginEmailPost"></a>
# **apiAuthLoginEmailPost**
> UserTokens apiAuthLoginEmailPost(userMailRegisterData)

login user

login user via email

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val userMailRegisterData : UserMailRegisterData =  // UserMailRegisterData | Email entered by the user in auth form
try {
    val result : UserTokens = apiInstance.apiAuthLoginEmailPost(userMailRegisterData)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#apiAuthLoginEmailPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#apiAuthLoginEmailPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userMailRegisterData** | [**UserMailRegisterData**](UserMailRegisterData.md)| Email entered by the user in auth form | [optional]

### Return type

[**UserTokens**](UserTokens.md)

### Authorization


Configure basicAuth:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="apiAuthLoginVkPost"></a>
# **apiAuthLoginVkPost**
> UserTokens apiAuthLoginVkPost(userVkRegisterData)

login user

login user via vk

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val userVkRegisterData : UserVkRegisterData =  // UserVkRegisterData | Email entered by the user in auth form
try {
    val result : UserTokens = apiInstance.apiAuthLoginVkPost(userVkRegisterData)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#apiAuthLoginVkPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#apiAuthLoginVkPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userVkRegisterData** | [**UserVkRegisterData**](UserVkRegisterData.md)| Email entered by the user in auth form | [optional]

### Return type

[**UserTokens**](UserTokens.md)

### Authorization


Configure basicAuth:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="apiAuthRefreshPost"></a>
# **apiAuthRefreshPost**
> UserTokens apiAuthRefreshPost(userRefreshToken)

register new user

register new user via email

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val userRefreshToken : UserRefreshToken =  // UserRefreshToken | User resfresh token
try {
    val result : UserTokens = apiInstance.apiAuthRefreshPost(userRefreshToken)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#apiAuthRefreshPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#apiAuthRefreshPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userRefreshToken** | [**UserRefreshToken**](UserRefreshToken.md)| User resfresh token | [optional]

### Return type

[**UserTokens**](UserTokens.md)

### Authorization


Configure basicAuth:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="apiAuthRegisterPost"></a>
# **apiAuthRegisterPost**
> UserTokens apiAuthRegisterPost(userMailRegisterData)

register new user

register new user via email

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = AuthApi()
val userMailRegisterData : UserMailRegisterData =  // UserMailRegisterData | Email entered by the user in auth form
try {
    val result : UserTokens = apiInstance.apiAuthRegisterPost(userMailRegisterData)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling AuthApi#apiAuthRegisterPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling AuthApi#apiAuthRegisterPost")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userMailRegisterData** | [**UserMailRegisterData**](UserMailRegisterData.md)| Email entered by the user in auth form | [optional]

### Return type

[**UserTokens**](UserTokens.md)

### Authorization


Configure basicAuth:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

