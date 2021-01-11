package com.seo.rxdemo.htt.req

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 작품 상세 정보 조회 요청 파라미터
 */
data class ReqWorksDetail(

    /**
     * 조회할 작품 번호
     */
    @Expose
    @SerializedName("wid")
    val wid: String,
    /**
     * 국가 코드
     */
    @Expose
    @SerializedName("cid")
    val cid: String,
    /**
     * 에피소드 언어 코드
     */
    @Expose
    @SerializedName("lang")
    val lang: String,
    /**
     * 사용자 id (optional)
     */
    @Expose
    @SerializedName("uid")
    val uid: String? = null
)