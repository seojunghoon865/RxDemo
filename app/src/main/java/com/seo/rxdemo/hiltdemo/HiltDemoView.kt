package com.seo.rxdemo.hiltdemo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.seo.rxdemo.di.DataA
import com.seo.rxdemo.di.FragmentData
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltDemoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {




    @Inject lateinit var dataA:DataA


}