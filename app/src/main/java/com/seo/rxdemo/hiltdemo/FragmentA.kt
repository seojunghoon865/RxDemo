package com.seo.rxdemo.hiltdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seo.rxdemo.R
import com.seo.rxdemo.ReqWorkData
import com.seo.rxdemo.di.DataA
import com.seo.rxdemo.di.DataB
import com.seo.rxdemo.di.FragmentData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_a.*
import javax.inject.Inject

@AndroidEntryPoint
class FragmentA  : Fragment() {

    companion object{
        private const val TAG = "Hilt";
    }

    @Inject lateinit var dataA:DataA
    @Inject lateinit var dataB:DataB
    @Inject lateinit var fragmentData: FragmentData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "FragmentA === onCreateView: ======")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "FragmentA === onViewCreated: ======")
        Log.d(TAG, "onViewCreated: dataA = ${dataA}")
        Log.d(TAG, "onViewCreated: dataB = ${dataB}")
        Log.d(TAG, "onViewCreated: framnentData = ${fragmentData}")
        Log.d(TAG, "onViewCreated: hiltDemoView.dataA = ${hiltDemoView.dataA}")
    }


}