package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResEpisode(
    /**
     * 에피소드 전체 갯수
     */
    @Expose
    @SerializedName("totalCount")
    val totalCount: Int = 0,
    @Expose
    @SerializedName("nList")
    val episodeList: List<EpisodeItem> = ArrayList()
):BaseResponse(){
    override fun toString(): String {
        return "ResEpisode(totalCount=$totalCount, episodeList=$episodeList)"
    }
}


data class EpisodeItem(
    /**
     * 작품번호
     */
    @Expose
    @SerializedName("wid")
    val wid: String = "",
    /**
     * 에피소드 id
     */
    @Expose
    @SerializedName("eid")
    val eid: String = "",
    /**
     * 에피소드 id
     */
    @Expose
    @SerializedName("episodesUnino")
    val episodesUnino: String = "",
    /**
     * 에피소드 제목
     */
    @Expose
    @SerializedName("title")
    val title: String = "",
    /**
     * 에피소드 종류
     * PROLOGUE, EPISODES, EPILOGUE
     */
    @Expose
    @SerializedName("kind")
    val kind: String = "",
    /**
     * 평점
     */
    @Expose
    @SerializedName("grade")
    val grade: Double = 0.0,
    /**
     * 유무료 여부
     * Y- 유료
     */
    @Expose
    @SerializedName("freechargeYn")
    val freechargeYn: String = "",
    /**
     * 가격(코인수)
     */
    @Expose
    @SerializedName("coinCount")
    val coinCount: Int = 0,
    /**
     * 에피소드 썸네일
     */
    @Expose
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    /**
     * 최종화 여부
     * Y-최종화
     */
    @Expose
    @SerializedName("finalYn")
    val finalYn: String = "",
    /**
     * 소장여부
     * Y-소장
     */
    @Expose
    @SerializedName("ownYn")
    val ownYn: String = "",
    /**
     * 에피소드 up
     * 죄회시점에 업데이트 됬는지 여부 Y-업데이트 됨
     */
    @Expose
    @SerializedName("up")
    val up: String = "",
    /**
     * 무료 이용권 사용 가능 여부
     * Y-무료권 사용 가능
     */
    @Expose
    @SerializedName("freeticketUseYn")
    val freeticketUseYn: String = "",
    /**
     * 작가 계정
     */
    @Expose
    @SerializedName("uid")
    val uid: String = "",
    /**
     * 연제 예정일
     */
    @Expose
    @SerializedName("publishSeriallyDate")
    val publishSeriallyDate: String = "",
    /**
     *  등록일
     */
    @Expose
    @SerializedName("regdate")
    val regdate: String = "",
    /**
     * 번역에피소드 여부
     * Y- 번역됨
     */
    @Expose
    @SerializedName("translationYn")
    val translationYn: String = "",
    /**
     * 연제미리보기 여부
     * Y-연제 미리보기
     */
    @Expose
    @SerializedName("freechargeIfPublishPreviewYn")
    val freechargeIfPublishPreviewYn: String = "",
    /**
     * 연제미리보기 잔여 일수
     * (ex. 9) 0이하인 경우 무료,
     *  freechargeIfPublishPreviewYn 가 Y 일경우만 체크
     */
    @Expose
    @SerializedName("fippRemainDays")
    val fippRemainDays: Int = 0,
    /**
     * 에피소스 시퀀스 넘버 (일단 에피소드 번호로 보자 ) 1부터 시작
     */
    @Expose
    @SerializedName("seqno")
    val seqno: Int = 0,
    /**
     * 원작에피소드 여부
     * Y- 원작 에피소드
     */
    @Expose
    @SerializedName("originalServiceCountry")
    val originalServiceCountry: String = "",
    /**
     * 기간한정 무료 여부
     * Y-기간한정무료
     */
    @Expose
    @SerializedName("freechargeIfPeriodLimitedYn")
    val freechargeIfPeriodLimitedYn: String = "",
    /**
     * 기간한정 무료 료 일경우 남은 기간(day : HH:mm:ss)정보
     * 단말에서 사용안함.
     */
    @Expose
    @SerializedName("fiplExpirationDate")
    val fiplExpirationDate: String = "",
    /**
     * 기간한정 무료 번호와 일치하는지 여부(간한정무료를 에피소드의 번호로 설정하는 경우 에 )
     * Y- 일치함
     *
     */
    @Expose
    @SerializedName("fiplEpisodesMatchYn")
    val fiplEpisodesMatchYn: String = "",
    /**
     * 완전무료 여부
     */
    @Expose
    @SerializedName("freechargeFullYn")
    val freechargeFullYn: String = "",
    /**
     * 무료권으로 구매했는지 여부
     * Y-무료권으로 구매함.
     */
    @Expose
    @SerializedName("freeticketViewYn")
    val freeticketViewYn: String = "",
    /**
     * 무료권으로 구매됬을시에 열람가능일
     */
    @Expose
    @SerializedName("freeticketExpirationDate")
    val freeticketExpirationDate: String = "",
    /**
     * 기다리면 무료권을 사용할수있는지 여부
     * 이성룡이사 : 기다리면 무료이용권을 보유하고, 해당 에피소드가 유료, 무료이용권사용가능여부Y,
     *           기다리면 무료 에피소드 구간인 경우에만 Y로 내려갑니다.
     */
    @Expose
    @SerializedName("fiwFreeticketUseYn")
    val fiwFreeticketUseYn: String = "",
    /**
     * 공고보면 무료권으로 볼수 있을때
     * Y- 광무 가능 에피소드임.
     */
    @Expose
    @SerializedName("fiaFreeticketUseYn")
    val fiaFreeticketUseYn: String = ""
)