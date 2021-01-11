package com.seo.rxdemo.hiltdemo

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.leanback.animation.LogDecelerateInterpolator
import com.jakewharton.rxbinding3.view.clicks
import com.seo.rxdemo.R
import com.seo.rxdemo.di.DataA
import com.seo.rxdemo.di.DataB
import com.seo.rxdemo.di.FragmentData
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_hilt_main.*
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class HiltMainActivity : AppCompatActivity() {

    @Inject lateinit var dataA:DataA


    @Inject lateinit var dataB:DataB




    private val hiltMainViewModel: HiltMainViewModel by viewModels()

    val compositeDisposable = CompositeDisposable()

    //private val hiltMainViewModel:HiltMainViewModel by hiltMainViewModel()



    companion object{
        private const val TAG = "Hilt";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_main)

        Log.d(TAG, "onCreate: dataA:${dataA}")
        Log.d(TAG, "onCreate: dataB:${dataB}")
        Log.d(TAG, "onCreate: hiltMainViewModel.retrofit = ${hiltMainViewModel.retrofit}")
        Log.d(TAG, "onCreate: hiltMainViewModel.deviceManager =  ${hiltMainViewModel.deviceManager}")

        progressLoading.visibility = View.GONE

        hiltMainViewModel.profileData.observe(this){
            Log.d(TAG, "onCreate: resUserDetails : ${it}")
        }

        hiltMainViewModel.requestState.observe(this){
            when (it){
                RequestState.START -> progressLoading.visibility = View.VISIBLE
                RequestState.END -> progressLoading.visibility = View.GONE
            }
        }


        val subscribe = btnSubActivity.clicks().subscribe {
            val intent = Intent(this,HiltSubActivity::class.java)
            startActivity(intent)
        }

        val btnSubscribe = Observable.merge(btnFr1.clicks().map { btnFr1 },btnFr2.clicks().map { btnFr2 })
                .subscribe {
                    changeFragment(it)
                }

        btnRequest.setOnClickListener {
            hiltMainViewModel.getUserProfile("inseo@seo.com")
        }

        compositeDisposable.add(subscribe)
    }

    fun changeFragment(btn:Button){

        var frgment:Fragment? = null

        when(btn){
            btnFr1->{
                frgment = FragmentA()
            }
            btnFr2->{
                frgment = FragmentB()
            }
        }

        frgment?.run {

            val tr = supportFragmentManager.beginTransaction()
            tr.replace(R.id.fragmentContent,frgment)
            tr.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}