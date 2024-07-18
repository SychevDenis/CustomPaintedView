package com.example.canvas_for_drawing.data.data_methods_activity

import java.util.Calendar
import javax.inject.Inject

class GetDateTime @Inject constructor() {
    fun getDateTime(formatFilePicture: String): String {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH) + 1 // Месяц начинается с 0
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        val second: Int = calendar.get(Calendar.SECOND)
        return "image_${year}_${month}_${day}_${hour}_" +
                "${minute}_${second}${formatFilePicture}" //имя создаваемого файла
    }
}