package com.farhad.techcrunch

import java.text.SimpleDateFormat

object Util {
    fun getIsoDate(sDate: String?): String? {
        return try {
            if (sDate == null) return null
            val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val date = df.parse(sDate)
            val df2 = SimpleDateFormat.getDateInstance()
            df2.format(date)
        } catch (e: Exception) {
            null
        }
    }
}