package com.seo.rxdemo.hiltdemo.bottom_navi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seo.rxdemo.R


class MyInfoFragment : Fragment(R.layout.fragment_my_info) {

    companion object {
        private const val TAG = "MyInfoFragment";
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MyInfoFragment::onCreate: ===> ")

    }



}