package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResUsersAge(
    /**
     * 연령코드
     */
    @Expose
    @SerializedName("code")
    val code: String = "",
    /**
     * 연령코드 이름
     */
    @Expose
    @SerializedName("name")
    val name: String = ""
)