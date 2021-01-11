package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResUserDetails(
    @Expose
    @SerializedName("uInfo")
    var userInfo: UserInfo?,
    @Expose
    @SerializedName("vInfo")
    var vInfo: VideoInfo?
) :BaseResponse(){
    override fun toString(): String {
        return "ResUserDetails(userInfo=$userInfo, vInfo=$vInfo)"
    }
}

data class VideoInfo(
    @Expose
    @SerializedName("participationYn")
    val participationYn: String?,
    @Expose
    @SerializedName("coinCount")
    var coinCount: Long,
    @Expose
    @SerializedName("openYn")
    val openYn: String?,
    @Expose
    @SerializedName("piece")
    var piece: Int,
    @Expose
    @SerializedName("productType")
    val productType: String?,
    @Expose
    @SerializedName("minimumViewCount")
    var minimumViewCount: Int,
    @Expose
    @SerializedName("maximumDailyCount")
    var maximumDailyCount: Int,
    @Expose
    @SerializedName("remainMinimumViewCount")
    var remainMinimumViewCount: Int,
    @Expose
    @SerializedName("remainMaximumDailyCount")
    var remainMaximumDailyCount: Int
)

data class UserInfo(
    @Expose
    @SerializedName("cid")
    val cid: String,
    @Expose
    @SerializedName("nickname")
    val nickname: String,
    @Expose
    @SerializedName("translationWriterYn")
    val translationWriterYn: String,
    @Expose
    @SerializedName("profile")
    val profile: String?,
    @Expose
    @SerializedName("snsProfileLink")
    val snsProfileLink: String?,
    @Expose
    @SerializedName("coinCount")
    var coinCount: Long,
    @Expose
    @SerializedName("point")
    var point: Long,
    @Expose
    @SerializedName("pointFloat")
    var pointFloat: Double,
    @Expose
    @SerializedName("freeticketCount")
    var freeticketCount: Int,
    @Expose
    @SerializedName("translationLanguage")
    val translationLanguage: String,
    @Expose
    @SerializedName("participationYn")
    val participationYn: String,
    @Expose
    @SerializedName("lang")
    val lang: String
)