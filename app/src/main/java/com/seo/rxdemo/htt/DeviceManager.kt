package com.seo.rxdemo.htt

import android.content.Context
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class DeviceManager (val context: Context) {

    companion object{
        private const val TAG = "DeviceInfo"
        const val os = "ANDROID"

    }


    val lang:String = getSystemLang(context)
    val uuid:String = getDeviceUUID(context)
    val appVersionName:String = getAppVersionName(context)
    val appVersionCode:Int = getAppVersionCode(context)
    var deviceToken:String = ""


    val modelName:String = Build.MODEL
    val device_OS = os


    private fun getSystemLang(context: Context):String{

        return "en"
    }


    private fun getDeviceUUID(context: Context): String {

        return "quick-test-udid"
    }

    /**
     * 앱 버전 가져오기
     * @return String
     */
    private fun getAppVersionName(context:Context): String {
        return "1.8.3"
    }

    /**
     * 앱 버전코드 가져오기
     * @return
     */
    private fun getAppVersionCode(context: Context): Int {
        return 91
    }

    override fun toString(): String {
        return "DeviceManager(context=$context, lang='$lang', uuid='$uuid', appVersionName='$appVersionName', appVersionCode=$appVersionCode, deviceToken='$deviceToken', modelName='$modelName', device_OS='$device_OS')"
    }


}