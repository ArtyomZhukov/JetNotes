package com.zhukovartemvl.jetnotes.common.utils

import java.text.SimpleDateFormat
import java.util.*


fun Long.toDateString(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val data = calendar.time
    return SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(data)
}
