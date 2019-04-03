package com.pvbapps.moviefy.ui.utils

import org.threeten.bp.DateTimeUtils
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DateUtils {
    companion object {
        fun createCalendar(date: Date): Calendar {
            var calendar = Calendar.getInstance()
            calendar.time = date
            return calendar
        }

        fun createZonedDateTime(date: Date): ZonedDateTime = DateTimeUtils.toZonedDateTime(createCalendar(date))

        fun createOffsetDateTime(date: Date): OffsetDateTime =
            createZonedDateTime(date).toOffsetDateTime()

        fun getMovieFormatDateString(string: String): String {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(string)
            return getDateString(createOffsetDateTime(date))
        }

        fun getDateString(date: OffsetDateTime): String = getStringFromFormat(date, "MMMM d, yyyy")

        fun getStringFromFormat(date: OffsetDateTime, format: String): String =
            date.format(DateTimeFormatter.ofPattern(format))
    }
}