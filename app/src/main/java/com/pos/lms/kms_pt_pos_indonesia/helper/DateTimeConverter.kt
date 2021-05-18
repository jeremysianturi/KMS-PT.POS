package com.pos.lms.kms_pt_pos_indonesia.helper

import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

    private fun currentDate(): Date {
        val calendar = Calendar.getInstance()
        return calendar.time
    }

    fun dateConverter(date: String): String {

        val dateResult: String

        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
        dateResult = formatter.format(parser.parse(date))



        return dateResult
    }

    fun dateWithDayConverter(date: String): String {

        val dateResult: String

        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("EEE, dd/MMM/yyyy", Locale.getDefault())
        dateResult = formatter.format(parser.parse(date))

        return dateResult
    }

    fun toHourMinutes(time : String) : String {
        val resultTime : String

        val parser = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        resultTime = formatter.format(parser.parse(time))

        return resultTime

    }

    fun timeAgo(date: Date): String {
        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = currentDate().time
        if (time > now || time <= 0) {
            return "in the future"
        }

        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> "moments ago"
            diff < 2 * MINUTE_MILLIS -> "a minute ago"
            diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS} minutes ago"
            diff < 2 * HOUR_MILLIS -> "an hour ago"
            diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS} hours ago"
            diff < 48 * HOUR_MILLIS -> "yesterday"
            else -> "${diff / DAY_MILLIS} days ago"
        }
    }

}