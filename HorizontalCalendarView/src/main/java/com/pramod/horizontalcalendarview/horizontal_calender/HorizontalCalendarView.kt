package com.pramod.horizontal_calender

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pramod.horizontalcalendarview.R
import java.util.*

class HorizontalCalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val recyclerView: RecyclerView
    private var adapter: DateAdapter? = null
    val dates = generateDates()
    //test

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_horizontal_calendar, this, true)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        setDates(dates)
    }

    fun setDates(dates: List<Date>) {
        adapter = DateAdapter(context, dates)
        recyclerView.adapter = adapter
    }
//    fun selectedDate(onDateSelected: (Date) -> Unit){
//        adapter = DateAdapter(context, onDateSelected)
//    }

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
