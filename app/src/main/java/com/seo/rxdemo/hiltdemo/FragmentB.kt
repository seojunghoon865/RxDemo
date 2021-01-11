package com.seo.rxdemo.hiltdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seo.rxdemo.R
import com.seo.rxdemo.di.DataA
import com.seo.rxdemo.di.DataB
import com.seo.rxdemo.di.FragmentData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class FragmentB : Fragment() {

    companion object{
        private const val TAG = "Hilt";
    }

    @Inject
    lateinit var dataA: DataA
    @Inject
    lateinit var dataB: DataB
    @Inject lateinit var fragmentData: FragmentData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "FragmentB === onViewCreated: ======")
        Log.d(TAG, "onViewCreated: dataA = ${dataA}")
        Log.d(TAG, "onViewCreated: dataB = ${dataB}")
        Log.d(TAG, "onViewCreated: framnentData = ${fragmentData}")
    }


}