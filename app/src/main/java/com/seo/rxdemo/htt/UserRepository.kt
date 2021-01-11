package com.seo.rxdemo.htt

import com.seo.rxdemo.htt.req.ReqEpisodesList
import com.seo.rxdemo.htt.req.ReqGeneralLoginParam
import com.seo.rxdemo.htt.req.ReqUserDetailsParam
import com.seo.rxdemo.htt.req.ReqWorksDetail
import com.seo.rxdemo.htt.res.ResEpisode
import com.seo.rxdemo.htt.res.ResGeneralLogin
import com.seo.rxdemo.htt.res.ResUserDetails
import com.seo.rxdemo.htt.res.ResWorksDetail
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface UserRepository {
    @POST("/m/generalLogin.json")
    fun generalLogin(@Body reqGeneralLoginParam: ReqGeneralLoginParam): Single<ResGeneralLogin>

    /**
     * 유저 프로필 정보 조회
     */
    @POST("/m/userDetails.json")
    fun userDetails(@Body reqUserDetailsParam: ReqUserDetailsParam): Single<ResUserDetails>


    /**
     * 작품 상세 정보
     */
    @POST("/m/worksDetail.json")
    fun worksDetail(@Body reqWorksDetail: ReqWorksDetail): Observable<ResWorksDetail>

    /**
     * 에피소드 리스트
     */
    @POST("/m/episodesList.json")
    fun episodeList(@Body reqEpisodesList: ReqEpisodesList): Observable<ResEpisode>


}