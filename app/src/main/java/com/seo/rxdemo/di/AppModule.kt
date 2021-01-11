package com.seo.rxdemo.di

import android.app.Application
import android.os.Build
import androidx.room.Room
import com.seo.rxdemo.HttpDemoActivity
import com.seo.rxdemo.TLSSocketFactory
import com.seo.rxdemo.db.AppDatabase
import com.seo.rxdemo.db.UserDao
import com.seo.rxdemo.htt.DeviceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataA() : DataA{
        return DataA("AAA")
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY;
        val builder = OkHttpClient.Builder()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) { // 2019-04-17 ysb: 5.0 단말 이하에서는 TLSSocketFactory 사용
            try {
                val tlsSocketFactory = TLSSocketFactory()
                builder.sslSocketFactory(tlsSocketFactory, tlsSocketFactory.trustManager)
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyStoreException) {
                e.printStackTrace()
            }
        }
        return builder.addInterceptor(logger)
            .connectTimeout(HttpDemoActivity.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HttpDemoActivity.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(HttpDemoActivity.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideProvideToryRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://test.torycomics.com:9062")
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDeviceInfo(application:Application) = DeviceManager(application)


    /**
     * Room Data
     */

//    @Provides
//    @Singleton
//    fun provideAppDatabase(application: Application):AppDatabase {
//        return Room.databaseBuilder(application,
//                AppDatabase::class.java, "appDB").build()
//    }

}