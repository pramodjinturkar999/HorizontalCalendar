package com.pramod.horizontalcalendar
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.pramod.horizontal_calender.HorizontalCalendarView


private lateinit var horizontalCalendarView: HorizontalCalendarView


class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horizontalCalendarView = findViewById(R.id.horizontalCalendarView)

        //test

    }


}
 