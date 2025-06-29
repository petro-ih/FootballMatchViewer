package com.footballmatchviewer.data.network

import com.footballmatchviewer.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain
            .request()
            .newBuilder()
            .addHeader(SPORTS_HEADER, BuildConfig.API_KEY)
            .build()
            .let(chain::proceed)

    private companion object {
        const val SPORTS_HEADER = "x-apisports-key"
    }
}
