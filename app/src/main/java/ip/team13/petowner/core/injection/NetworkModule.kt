package ip.team13.petowner.core.injection

import ip.team13.petowner.BuildConfig
import ip.team13.petowner.core.network.AuthInterceptor
import ip.team13.petowner.data.PetOwnerAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { AuthInterceptor(get()) }
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
            .addInterceptor(get(AuthInterceptor::class.java))
            .build()
    }
    single {
        get(Retrofit::class.java).create(PetOwnerAPI::class.java)
    }
    single {
        Retrofit.Builder().baseUrl("http://146.59.3.206/api/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
