package com.seo.rxdemo.htt.req

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 사용자 상세정보 조회 파라미터
 */
data class ReqUserDetailsParam(


    /**
     * 사용자 id
     */
    @Expose
    @SerializedName("uid")
    val uid: String,
    /**
     * 단말 uuid
     */
    @Expose
    @SerializedName("deviceId")
    val deviceId: String,
    /**
     * 단말 언어코드
     */
    @Expose
    @SerializedName("lang")
    val lang: String,
    /**
     * 포인트 정보 실수로 요청
     */
    @Expose
    @SerializedName("pointFloatYn")
    val pointFloatYn: String = "Y"

)