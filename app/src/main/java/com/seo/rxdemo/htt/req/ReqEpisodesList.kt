package com.seo.rxdemo.htt.req

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 에피소드 리스트 요청 파라미터
 */
data class ReqEpisodesList(
    /**
     * 국가 코드
     */
    @Expose
    @SerializedName("cid")
    val cid: String,
    /**
     * 작품번호
     */
    @Expose
    @SerializedName("wid")
    val wid: String,



    /**
     * 정렬 : ASC(첫편부터), DESC(최신)
     */
    @Expose
    @SerializedName("sort")
    val sort: String,
    /**
     * 에피소드 보기 언어 코드
     */
    @Expose
    @SerializedName("lang")
    val lang: String,
    /**
     * Y = 미번역 포함
     * N = 번역본만
     */
    @Expose
    @SerializedName("noTrans")
    val noTrans: String,

    /**
     * 사용자 id
     */
    @Expose
    @SerializedName("uid")
    val uid: String? = null
){
    /**
     * 페이지 번호
     */
    @Expose
    @SerializedName("page")
    var page: Int = 1
    /**
     * 페이지 요청 사이즈
     */
    @Expose
    @SerializedName("pageSize")
    var pageSize: Int = 30

    /**
     * 에피소드 전체 count
     * page 가 1이상이면 무조건 필수
     */
    @Expose
    @SerializedName("totalCount")
    var totalCount: Int? = null
}