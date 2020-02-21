package com.traiingtrack.top10dowloader

import android.util.Log

class ParseApplications {
    private val TAG = "parseApplication"
    val applications = ArrayList<FeedEntry>()

    fun parse(xmlData: String): Boolean{
        Log.d(TAG, "parse called with $xmlData")
        var status = true
        var inEntry = false
        var textValue = ""

        try{

        }catch{

        }
    }

}