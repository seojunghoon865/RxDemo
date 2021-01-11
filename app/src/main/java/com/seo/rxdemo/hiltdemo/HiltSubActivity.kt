package com.seo.rxdemo.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seo.rxdemo.R
import com.seo.rxdemo.di.DataA
import com.seo.rxdemo.di.DataB
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltSubActivity : AppCompatActivity() {

    @Inject lateinit var dataA:DataA
    @Inject lateinit var dataB:DataB

    companion object{
        private const val TAG = "Hilt";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt_sub)

        Log.d(TAG, "onCreate: dataA : ${dataA}")
        Log.d(TAG, "onCreate: dataB:${dataB}")
    }
}