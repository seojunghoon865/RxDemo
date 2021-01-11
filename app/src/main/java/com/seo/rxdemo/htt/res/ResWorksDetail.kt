package com.seo.rxdemo.htt.res

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResWorksDetail(
    @Expose
    @SerializedName("wInfo")
    var worksInfo: WorksInfo? = null

):BaseResponse(){
    override fun toString(): String {
        return "ResWorksDetail(worksInfo=$worksInfo)"
    }
}

data class WorksInfo(
    /**
     * 작품 번호
     */
    @Expose
    @SerializedName("wid")
    val wid: String = "",
    /**
     * 작품타이틀
     */
    @Expose
    @SerializedName("title")
    val title: String = "",
    /**
     * 작품등록일
     */
    @Expose
    @SerializedName("regdate")
    val regdate: String = "",
    /**
     * 원작작품여부 Y-원작
     */
    @Expose
    @SerializedName("originalServiceCountry")
    val originalServiceCountry: String = "",
    /**
     * 작가명
     */
    @Expose
    @SerializedName("username")
    val username: String = "",
    /**
     * 회사명
     */
    @Expose
    @SerializedName("company")
    val company: String = "",
    /**
     * 작품설명
     */
    @Expose
    @SerializedName("description")
    val description: String = "",
    /**
     * 작품연제 상태
     * ING(연재중), PAUSE(휴재), CLOSED(완결)
     */
    @Expose
    @SerializedName("publishStatus")
    val publishStatus: String = "",
    /**
     * 이용연령
     */
    @Expose
    @SerializedName("usersAge")
    val resUsersAge: List<ResUsersAge> = ArrayList(),
    /**
     * 이벤트 공지 내용
     */
    @Expose
    @SerializedName("eventContent")
    val eventContent: String = "",
    /**
     * 이벤트 링크
     */
    @Expose
    @SerializedName("eventLink")
    val eventLink: String = "",
    /**
     * 연제요일 정보
     */
    @Expose
    @SerializedName("days")
    val days: List<ResDay> = ArrayList(),
    /**
     * 장르 정보
     */
    @Expose
    @SerializedName("genre")
    val genre: List<ResGenre> = ArrayList(),
    /**
     * 평점
     */
    @Expose
    @SerializedName("grade")
    val grade: Double = 0.0,
    /**
     * 신규작품 여부
     * Y- 신규작품
     */
    @Expose
    @SerializedName("new")
    val isNew:String = "",
    /**
     * 원작에피소드 업데이트 여부
     * Y-업데이트 됨
     */
    @Expose
    @SerializedName("up")
    val isUp: String = "",
    /**
     * 번역에피소드 업데이트 여부
     * Y-업데이트 됨
     */
    @Expose
    @SerializedName("transNew")
    val transNew: String = "",
    /**
     * 열람수
     */
    @Expose
    @SerializedName("queryCount")
    val queryCount: Int = 0,
    /**
     * 인기도
     */
    @Expose
    @SerializedName("popularity")
    val popularity: String = "0",
    /**
     * 작품썸네일
     * small
     */
    @Expose
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    /**
     * 작품썸네일
     * Big
     */
    @Expose
    @SerializedName("newThumbnail")
    val newThumbnail: String = "",
    /**
     * 마지막 에피소드 등록일
     */
    @Expose
    @SerializedName("episodesUpddate")
    val episodesUpddate: String = "",
    /**
     * 댓글수
     */
    @Expose
    @SerializedName("commentCount")
    val commentCount: Int = 0,
    /**
     * 구독여부
     */
    @Expose
    @SerializedName("subscriptionYn")
    val subscriptionYn: String = "",
    /**
     * 일괄구매 가능 여부
     */
    @Expose
    @SerializedName("bundleYn")
    val bundleYn: String = "",
    /**
     * 일괄구매시 최소 에피소드 구매 갯수
     */
    @Expose
    @SerializedName("minimumEpisodesCount")
    val minimumEpisodesCount: Int = 0,
    /**
     * 일괄 구매시 할인율
     */
    @Expose
    @SerializedName("discount")
    val discount: Int = 0,
    /**
     * 공유하면 무료 여부
     * Y-공유하면 무료
     */
    @Expose
    @SerializedName("freechargeIfShareYn")
    val freechargeIfShareYn: String = "",
    /**
     * 기간한정 무료 여부
     * Y-기간한정 무료
     */
    @Expose
    @SerializedName("freechargeIfPeriodLimitedYn")
    val freechargeIfPeriodLimitedYn: String = "",
    /**
     * 연제마리보기 여부
     * Y-연제미리보기
     */
    @Expose
    @SerializedName("freechargeIfPublishPreviewYn")
    val freechargeIfPublishPreviewYn: String = "",
    /**
     * 기다리면 무료 여부
     * Y- 기다리면 무료
     */
    @Expose
    @SerializedName("freechargeIfWaitingYn")
    val freechargeIfWaitingYn: String = "",
    /**
     * 광고보면 무료 여부
     * Y- 광고보면 무료
     */
    @Expose
    @SerializedName("freechargeIfAdvertiseSeeYn")
    val freechargeIfAdvertiseSeeYn: String = "",

    /**
     * 완전 무료 여부
     * Y- 완전무료
     */
    @Expose
    @SerializedName("freechargeFullYn")
    val freechargeFullYn: String = "",

    /**
     * 공유하면 무료(일수)
     */
    @Expose
    @SerializedName("fisLimitedDays")
    val fisLimitedDays: Int = 0,
    /**
     * 공유하면 무료(발행 매수)
     */
    @Expose
    @SerializedName("fisIssueCount")
    val fisIssueCount: Int = 0,
    /**
     * 공유하면무료(열람가능 일수)
     */
    @Expose
    @SerializedName("fisReadingDays")
    val fisReadingDays: Int = 0,
    /**
     * 기간한정 무료 에피소드 index 정보
     */
    @Expose
    @SerializedName("fiplEpisodesIndex")
    val fiplEpisodesIndex: String = "",

    /**
     * 기간한정 무료 시작일
     */
    @Expose
    @SerializedName("fiplExpirationFromdate")
    val fiplExpirationFromdate: String = "",

    /**
     * 기간한정 무료 종료일
     */
    @Expose
    @SerializedName("fiplExpirationTodate")
    val fiplExpirationTodate: String = "",
    /**
     * 기간한정 무료 프롤로그 포함 여부
     * Y-포함됨
     */
    @Expose
    @SerializedName("fiplPrologYn")
    val fiplPrologYn: String = "",
    /**
     *  기간한정 무료 에필로그 포함 여부
     *  Y-포함됨
     */
    @Expose
    @SerializedName("fiplEpilogYn")
    val fiplEpilogYn: String = "",
    /**
     * 연재미리보기(~일 후 무료)
     */
    @Expose
    @SerializedName("fippAfterDays")
    val fippAfterDays: Int = 0,
    /**
     * 연제미리보기(에피소드 시작 번호)
     */
    @Expose
    @SerializedName("fippEpisodesIndex")
    val fippEpisodesIndex: Int = 0,
    /**
     * 연제미리보기 (적용일 YYYY-MM-DD)
     */
    @Expose
    @SerializedName("fippDate")
    val fippDate: String = "",
    /**
     * 기다리면 무료(일 간격, 1매 1일 열람)
     */
    @Expose
    @SerializedName("fiwWaitingDays")
    val fiwWaitingDays: Int = 0,
    /**
     * 기다리면 무료 - 열람 가능 시간 (<= 0 이면 무료 열람 가능)
     */
    @Expose
    @SerializedName("fiwRemainHours")
    val fiwRemainHours: Int = 0,
    /**
     * 기다리면 무료 - 최신 * 화 제외
     */
    @Expose
    @SerializedName("fiwEpisodesIndex")
    val fiwEpisodesIndex: Int = 0,
    /**
     * 기다리면 무료권의 다음 발급일
     */
    @Expose
    @SerializedName("fiwNextIssueDate")
    val fiwNextIssueDate: String = "",
    /**
     * 기다리면 무료권 갯수
     */
    @Expose
    @SerializedName("fiwFreeticketCount")
    val fiwFreeticketCount: Int = 0,
    /**
     * 광고보면 무료 최신화차 제한 갯수
     */
    @Expose
    @SerializedName("fiaEpisodesIndex")
    val fiaEpisodesIndex: Int = 0,

    /**
     * 완전무료 시작일시
     */
    @Expose
    @SerializedName("ffFromdate")
    val ffFromdate: String = "",
    /**
     * 완전무료 종료 일시
     */
    @Expose
    @SerializedName("ffFodate")
    val ffFodate: String = "",
    /**
     * 기다리면 무료권 갯수
     */
    @Expose
    @SerializedName("freeticketCount")
    val freeticketCount: Int = 0,
    @Expose
    @SerializedName("oTitle")
    /**
     * 원작 작품 제목
     */
    val oTitle: String = "",
    @Expose
    @SerializedName("oDescription")
    val oDescription: String = "",
    /**
     * 원작 국가의 언어
     */
    @Expose
    @SerializedName("lang")
    val lang: String = ""
)


