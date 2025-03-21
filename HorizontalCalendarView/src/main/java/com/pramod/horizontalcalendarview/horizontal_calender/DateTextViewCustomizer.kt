package com.pramod.horizontalcalendarview.horizontal_calender

import android.widget.TextView
import java.util.Date

interface DateTextViewCustomizer {
    fun customize(textView: TextView, date: Date, isSelected: Boolean)
}