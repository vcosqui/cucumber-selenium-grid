package com.vcosqui.cucumber.support.extensions

interface WithPageUrl {
    fun pageUrl(): String = baseUrl()
    fun baseUrl(): String
}