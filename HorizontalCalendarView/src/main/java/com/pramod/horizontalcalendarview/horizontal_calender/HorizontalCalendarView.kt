package com.pramod.horizontal_calender

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pramod.horizontalcalendarview.R
import com.pramod.horizontalcalendarview.horizontal_calender.DateTextViewCustomizer
import java.util.*
import androidx.core.content.withStyledAttributes

class HorizontalCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val recyclerView: RecyclerView
    var adapter: DateAdapter? = null
    val dates = generateDates()

    var dateTextColor: Int = Color.BLACK
    var dateTextSize: Float = 14f
    var dateTextStyle: Int = Typeface.NORMAL
    var selectedDateTextColor: Int = Color.WHITE
    var selectedDateBackgroundColor: Int = Color.BLUE

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_horizontal_calendar, this, true)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        context.theme.obtainStyledAttributes(attrs, R.styleable.HorizontalCalendarView, 0, 0).apply {
            try {
                dateTextColor = getColor(R.styleable.HorizontalCalendarView_dateTextColor, Color.BLACK)
                dateTextSize = getDimension(R.styleable.HorizontalCalendarView_dateTextSize, 14f)
                dateTextStyle = getInt(R.styleable.HorizontalCalendarView_dateTextStyle, Typeface.NORMAL)
                selectedDateTextColor = getColor(R.styleable.HorizontalCalendarView_selectedDateTextColor, Color.WHITE)
                selectedDateBackgroundColor = getColor(R.styleable.HorizontalCalendarView_selectedDateBackgroundColor, Color.BLUE)
            } finally {
                recycle()
            }
        }

        setDates(dates)
    }

    fun setDates(dates: List<Date>) {
        adapter = DateAdapter(context, dates, null, this)
        recyclerView.adapter = adapter
    }

    fun clearSelection() {
        adapter?.clearSelection()
    }

    fun generateDates(): List<Date> {
        val calendar = Calendar.getInstance()
        val dates = mutableListOf<Date>()
        for (i in 0 until 100) {
            dates.add(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return dates
    }
}
