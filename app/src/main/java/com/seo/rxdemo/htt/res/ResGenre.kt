package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 장르
 */
data class ResGenre(
    /**
     * 장르코드
     */
    @Expose
    @SerializedName("code")
    val code: String = "",

    /**
     * 장르명
     */
    @Expose
    @SerializedName("name")
    val name: String = ""
)