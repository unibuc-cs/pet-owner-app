package ip.team13.petowner.core.network

import ip.team13.petowner.core.persistence.Preferences
import okhttp3.Interceptor

class AuthInterceptor(private val preferences: Preferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) =
        chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${preferences.getBearerToken()}")
                .build()
        )
}