package ip.team13.petowner.core.network

import ip.team13.petowner.BuildConfig
import ip.team13.petowner.data.PetOwnerAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    factory { AuthInterceptor(get()) }
    factory { provideOkHttpClient(get()) }
    factory { providePetOwnerApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://146.59.3.206/api/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        })
        .addInterceptor(authInterceptor)
        .build()
}

fun providePetOwnerApi(retrofit: Retrofit): PetOwnerAPI = retrofit.create(PetOwnerAPI::class.java)
