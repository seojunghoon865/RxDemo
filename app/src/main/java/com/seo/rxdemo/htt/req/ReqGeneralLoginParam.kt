package com.seo.rxdemo.htt.req

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.seo.rxdemo.htt.DeviceManager


/**
 * 일반로그인 요청 파라미터
 */
class ReqGeneralLoginParam(uid:String, password:String, deviceManager: DeviceManager){


    /**
     * 사용자 아이디
     */
    @Expose
    @SerializedName("uid")
    val uid: String = uid

    /**
     * 비밀번호
     */
    @Expose
    @SerializedName("passwd")
    val passwd: String = password

    /**
     * 단말 언어
     */
    @Expose
    @SerializedName("lang")
    val lang: String = deviceManager.lang

    /**
     * FCM deviceToken
     */
    @Expose
    @SerializedName("deviceToken")
    var deviceToken: String? = deviceManager.deviceToken

    /**
     * 단말 uuid
     */
    @Expose
    @SerializedName("deviceId")
    val deviceId: String = deviceManager.uuid

    /**
     * 단말 os 정보
     */
    @Expose
    @SerializedName("os")
    val os: String = deviceManager.device_OS

    /**
     * 단말 모델명
     */
    @Expose
    @SerializedName("model")
    val model: String = deviceManager.modelName

    /**
     * app version
     */
    @Expose
    @SerializedName("appVersion")
    val appVersion: String = deviceManager.appVersionName
}

