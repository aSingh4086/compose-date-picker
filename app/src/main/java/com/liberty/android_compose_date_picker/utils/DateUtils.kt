package com.liberty.android_compose_date_picker.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun formatDate(
        date: String,
        format: String = "yyyy-MM-dd",
    ): LocalDate {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return LocalDate.parse(date, dateFormat)
    }

    fun formatDateTime(
        date: String,
        format: String = "yyyy-MM-dd HH:mm",
    ): LocalDateTime {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return LocalDateTime.parse(date, dateFormat)
    }

    fun now(timeZone: TimeZone = TimeZone.getDefault()): LocalDate = LocalDate.now(timeZone.toZoneId())

    fun now(timeZone: String): LocalDate = LocalDate.now(TimeZone.getTimeZone(timeZone).toZoneId())

    fun nowFormatted(formatterPattern: String): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(formatterPattern)
        return currentDateTime.format(formatter)
    }

    fun zonedDateTime(dateString: String): ZonedDateTime {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        return ZonedDateTime.parse(dateString, formatter)
    }

    fun formatDateToShortStyle(date: LocalDate): String {
        val dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val month = date.month.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val dayOfMonth = date.dayOfMonth
        return "$dayOfWeek, $month $dayOfMonth"
    }
}

class DateTimeServices(
    val date: String,
    private val dateFormat: String = DateTimeFormatter.ISO_DATE.toFormat().toString(),
) {
    private var baseDate: LocalDate? = null

    init {
        val format = DateTimeFormatter.ofPattern(dateFormat)
        baseDate = LocalDate.parse(date, format)
    }

    fun formatDate(format: String = ""): DateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)

    fun isSameOrAfter(currentDate: LocalDate): Boolean = baseDate?.isAfter(currentDate) == true || baseDate == currentDate

    /**
     * Converts a date string to a LocalDate object.
     *
     * @param date The date string to be converted.
     * @param type return type of days/months/years.
     * @return The LocalDate object parsed from the date string, or null if parsing fails.
     */
    fun diff(
        date: LocalDate,
        type: String,
    ): Int {
        val period = Period.between(baseDate, date)
        return when (type.lowercase()) {
            "days" -> period.days
            "months" -> period.months
            "years" -> period.years
            else -> throw IllegalArgumentException("Invalid type: $type")
        }
    }

    fun isBefore(compareDate: LocalDate): Boolean = baseDate?.isBefore(compareDate) == true
}
