package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResDay(
    /**
     * 요일코드
     */
    @Expose
    @SerializedName("code")
    val code: String = "",
    /**
     * 요일명
     */
    @Expose
    @SerializedName("name")
    val name: String = ""
)