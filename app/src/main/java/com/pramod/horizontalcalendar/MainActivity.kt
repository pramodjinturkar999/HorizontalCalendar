package com.pramod.horizontalcalendar
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.pramod.horizontal_calender.HorizontalCalendarView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


private lateinit var horizontalCalendarView: HorizontalCalendarView

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horizontalCalendarView = findViewById(R.id.horizontalCalendarView)
         val startDate = Calendar.getInstance().time
         val endDate = Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.time

        horizontalCalendarView.setStartDate(startDate)
        horizontalCalendarView.setEndDate(endDate)

        horizontalCalendarView.getSelectedDate { selectedDate ->
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate)
            Log.d(TAG, "Selected Date: $formattedDate")
            Log.d(TAG, "Selected Date:: $selectedDate")
        }


    }

}
 