package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class
BaseResponse {
    @Expose
    @SerializedName("resultCode")
    var resultCode: Int? = null
    @Expose
    @SerializedName("resultMessage")
    var resultMessage: String? = null
}