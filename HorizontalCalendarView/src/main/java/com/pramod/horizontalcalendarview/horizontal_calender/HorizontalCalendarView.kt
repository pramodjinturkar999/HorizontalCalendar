package com.pramod.horizontal_calender

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
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
    private var startDate = Calendar.getInstance().time
    private var endDate = Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.time
    private var dates: List<Date> = listOf()

    // Attributes
    private var dateTextColor: Int = Color.BLACK
    private var dateTextSize: Float = 14f
    private var dateTextStyle: Typeface? = null
    private var selectedDateTextColor: Int = Color.WHITE
    private var selectedDateBackgroundColor: Int = Color.BLUE


    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.view_horizontal_calendar, this, true)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        context.theme.obtainStyledAttributes(attrs, R.styleable.HorizontalCalendarView, 0, 0).apply {
            try {
                dateTextColor = getColor(R.styleable.HorizontalCalendarView_dateColor, Color.BLACK)
                dateTextSize = getDimension(R.styleable.HorizontalCalendarView_dateSize, 14f)
                selectedDateTextColor = getColor(
                    R.styleable.HorizontalCalendarView_selectedDateColor,
                    Color.WHITE
                )
                selectedDateBackgroundColor = getColor(
                    R.styleable.HorizontalCalendarView_selectedDateBackgroundColor,
                    Color.CYAN
                )
            } finally {
                recycle()
            }
        }

        setDates()  // Ensure dates are set immediately
        setupAdapterForPreview() // Ensure adapter updates with new values
    }


    // Sample data for XML preview
    private fun generateSampleDates(): List<Date> {
        val calendar = Calendar.getInstance()
        return List(7) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            calendar.time
        }
    }

    private fun setupAdapterForPreview() {
        adapter = DateAdapter(
            context,
            dates,
            {}, // Empty callback for preview
            dateTextColor,
            dateTextSize,
            selectedDateTextColor,
            selectedDateBackgroundColor
        )
        recyclerView.adapter = adapter
    }
    private fun setDates() {
        dates = generateDates()

        // Create a new adapter with updated values
        adapter = DateAdapter(
            context,
            dates,
            {}, // Empty callback for preview
            dateTextColor,
            dateTextSize,
            selectedDateTextColor,
            selectedDateBackgroundColor
        )
        recyclerView.adapter = adapter
    }



    fun getSelectedDate(onDateSelected: (Date) -> Unit) {
        adapter = DateAdapter(
            context,
            dates,
            onDateSelected,
            dateTextColor,
            dateTextSize,
            selectedDateTextColor,
            selectedDateBackgroundColor
        )
        recyclerView.adapter = adapter
    }

    fun setStartDate(date: Date) {
        startDate = date
        setDates()
    }

    fun setEndDate(date: Date) {
        endDate = date
        setDates()
    }

    fun generateDates(): List<Date> {
        val calendar = Calendar.getInstance().apply {
            time = startDate ?: Calendar.getInstance().time
        }

        val defaultEndDate = Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.time
        val effectiveEndDate = endDate ?: defaultEndDate

        val dates = mutableListOf<Date>()
        while (calendar.time.before(effectiveEndDate) || calendar.time == effectiveEndDate) {
            dates.add(calendar.time)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return dates
    }



}
