package com.demate.noteapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat(/* pattern = */ "yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return format.format(date)

}