package br.com.franco.lucas.teste.testerunrunit.utils

import android.content.Context
import android.net.ConnectivityManager
import br.com.franco.lucas.teste.testerunrunit.ManagerApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by MacMini on 14/03/2018.
 */

class AuthRetrofit {

    // Header with appKey ans userToken
    var interceptorRegister: Interceptor = Interceptor { chain ->
        val original = chain.request().newBuilder()
        original
                .addHeader("App-Key", "b2cb937022edb22c2a7268846a93a143")
                .addHeader("User-Token", "Bn0T1DX7csAC4Na5nZsI")
        chain.proceed(original.build())
    }

    fun request(): Retrofit {

        val builder = OkHttpClient.Builder()
                .addInterceptor(this.interceptorRegister)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build()).build()
    }

}
