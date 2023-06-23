/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package org.openapitools.client.models

import org.openapitools.client.models.NewsCategory
import org.openapitools.client.models.NewsHeading

import com.squareup.moshi.Json

/**
 * 
 *
 * @param id 
 * @param title 
 * @param info 
 * @param heading 
 * @param category 
 * @param imageUrl 
 */


data class NewsShortData (

    @Json(name = "id")
    val id: kotlin.Int,

    @Json(name = "title")
    val title: kotlin.String,

    @Json(name = "info")
    val info: kotlin.String,

    @Json(name = "heading")
    val heading: NewsHeading,

    @Json(name = "category")
    val category: NewsCategory,

    @Json(name = "imageUrl")
    val imageUrl: kotlin.String? = null

)

