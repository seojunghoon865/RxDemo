package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResGeneralLogin(
    @Expose
    @SerializedName("uInfo")
    val uInfo: UGeneralLoginInfo?
):BaseResponse()

data class UGeneralLoginInfo(
    @Expose
    @SerializedName("cdata")
    val cdata: Int?,
    @Expose
    @SerializedName("cid")
    val cid: String?,
    @Expose
    @SerializedName("translationWriterYn")
    val translationWriterYn: String?,
    @Expose
    @SerializedName("lang")
    val lang: String?
)