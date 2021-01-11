package com.seo.rxdemo.util

fun String.Cipher():String{
    return Cipher.encryptString(this,true)
}