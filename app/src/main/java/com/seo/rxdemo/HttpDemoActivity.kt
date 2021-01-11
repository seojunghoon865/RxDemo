package com.seo.rxdemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seo.rxdemo.htt.DeviceManager
import com.seo.rxdemo.htt.UserRepository
import com.seo.rxdemo.htt.req.*
import com.seo.rxdemo.htt.res.*
import com.seo.rxdemo.util.Cipher
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_http_demo.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

class HttpDemoActivity : AppCompatActivity() {



    companion object {

        const val CONNECT_TIMEOUT = 30L


        const val WRITE_TIMEOUT = 30L


        const val READ_TIMEOUT = 30L

        private const val TAG = "HttpDemoActivity";
    }

    private val disposables = CompositeDisposable()
    lateinit var retrofit: Retrofit

    lateinit var userRepository: UserRepository

    val logAdepter = LogAdepter()

    val logSubject:PublishSubject<String> = PublishSubject.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_demo)
        retrofit = provideToryRetrofitInstance(okHttpClient())

        userRepository = retrofit.create(UserRepository::class.java)


        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //linearLayoutManager.stackFromEnd = true
        logRecycler.layoutManager = linearLayoutManager
        logRecycler.adapter = logAdepter

        val subscribe = logSubject
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                logAdepter.addLog(it)
                    logRecycler.scrollToPosition(logAdepter.itemCount -1)

        }
        disposables.add(subscribe)



        btnLogin.setOnClickListener {
            loginTest("inseo@seo.com","123456")
        }

        btnMerge.setOnClickListener {
            mergeTest()
        }

        btnZip.setOnClickListener {
            zipTest()
        }

        btnClear.setOnClickListener {
            logAdepter.clearAll()
        }


    }

    private fun zipTest() {
        val subscribe = Observable.zip(
                userRepository.worksDetail(ReqWorksDetail("WT_01015", "id", "in",
                        "inseo@seo.com".Cipher())).subscribeOn(Schedulers.io()),
                userRepository.episodeList(ReqEpisodesList("in", "WT_01015", "ASC", "in", "Y",
                        "inseo@seo.com".Cipher())).subscribeOn(Schedulers.io()),
                { reqDetail, reqEpisode, ->
                    Log.d(TAG, "zipTest: reqDetail, reqEpisode, =========>")
                    Log.d(TAG, "zipTest: reqDetail : ${reqDetail.toString()}")
                    Log.d(TAG, "zipTest: reqEpisode : ${reqEpisode.toString()}")
                    ReqWorkData(reqDetail, reqEpisode)
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "zipTest: subscribe it = ${it}")
                }){
                    error ->
                    Log.d(TAG, "zipTest: error -> ${error.toString()}")
                }
        disposables.add(subscribe)
    }

    private fun mergeTest() {

        val subscribe = Observable.merge(userRepository.worksDetail(ReqWorksDetail("WT_01015", "id", "in",
                "inseo@seo.com".Cipher())).subscribeOn(Schedulers.io()),
                userRepository.episodeList(ReqEpisodesList("in", "WT_01015", "ASC", "in", "Y",
                        "inseo@seo.com".Cipher())).subscribeOn(Schedulers.io())
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d(TAG, "mergeTest: mergeTest subscribe = ${it.resultCode}")

                    when(it){
                        is ResWorksDetail -> {
                            val resWorksDetail = it as? ResWorksDetail
                            Log.d(TAG, "mergeTest: ${resWorksDetail.toString()}")
                        }
                        is ResEpisode -> {
                            val resEpisode = it as? ResEpisode
                            Log.d(TAG, "mergeTest: resEpisode = ${resEpisode.toString()}")
                        }
                    }


                }

        disposables.add(subscribe)

    }

    fun loginTest(uid:String, password:String){

        val dm = DeviceManager(this)



        val subscribe = userRepository
                .generalLogin(ReqGeneralLoginParam(uid.Cipher(), password.Cipher(), dm))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap{
                    addLog("====== flatMap ======= ")
                    val cdata:Int = it.uInfo?.cdata ?: -1
                    addLog("cdata : $cdata")
                    //Thread.sleep(5000)
                    when(cdata){
                        201,202,203 -> {
                            userRepository.userDetails(ReqUserDetailsParam(uid.Cipher(), dm.uuid, dm.lang))
                                .subscribeOn(Schedulers.io())
                        }
                        else->{
                            Single.just(it)
                        }
                    }
                }
                .doOnSubscribe {
                    Log.d(TAG, "loginTest: doOnSubscribe =====>")
                    addLog("loginTest: doOnSubscribe =====>")
                }
                .doAfterTerminate {
                    Log.d(TAG, "loginTest: doAfterTerminate ====>")
                    addLog("loginTest: doAfterTerminate ====>")
                }
                .subscribe({
                    Log.d(TAG, "loginTest: onSucessess =====>>")
                    Log.d(TAG, "loginTest: it = ${it.resultCode}")
                    addLog("===== subscribe =====")
                    addLog("loginTest: it = ${it.resultCode}")

                    val resUserDetails: ResUserDetails? = it as? ResUserDetails
                    Log.d(TAG, "loginTest: resUserDetails:${resUserDetails.toString()}")
                    addLog("loginTest: resUserDetails:${resUserDetails.toString()}")


                }) { error ->
                    Log.d(TAG, "loginTest: error = ${error.toString()}")
                }
        disposables.add(subscribe)
    }


    fun okHttpClient(): OkHttpClient {
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
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    fun provideToryRetrofitInstance(client:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://test.torycomics.com:9062")
            .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun addLog(text:String){
        logSubject.onNext(text)
    }


    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}


data class ReqWorkData(val resWorksDetail:ResWorksDetail,val resEpisode:ResEpisode)


class LogAdepter: RecyclerView.Adapter<LogViewHolder>() {

    private val logArray:ArrayList<String> = ArrayList()

    fun addLog(text:String){
        logArray.add(text)
        notifyDataSetChanged()
    }

    fun clearAll(){
        logArray.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        return LogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.loglist_item,parent,false))
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.setData(logArray[position])
    }

    override fun getItemCount(): Int {
        return logArray.size
    }

}

class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textLog:TextView = itemView.findViewById(R.id.textLog)

    fun setData(text:String){
        textLog.text = text
    }
}