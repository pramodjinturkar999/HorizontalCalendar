package com.pramod.horizontal_calender

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.pramod.horizontalcalendarview.R
import com.pramod.horizontalcalendarview.horizontal_calender.DateTextViewCustomizer
import java.text.SimpleDateFormat
import java.util.*


class DateAdapter(
    private val context: Context,
    private var dates: List<Date> = emptyList(),
    private val onDateSelected: ((Date) -> Unit)? = null,
    private val dateTextColor: Int,
    private val dateTextSize: Float,
    private val selectedDateTextColor: Int,
    private val selectedDateBackgroundColor: Int
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    private var selectedPosition = RecyclerView.NO_POSITION

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.date)

        fun bind(date: Date, isSelected: Boolean) {
            dateTextView.text = dateFormat.format(date)
            dateTextView.setTextColor(if (isSelected) selectedDateTextColor else dateTextColor)
            dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, dateTextSize)
            dateTextView.setBackgroundColor(if (isSelected) selectedDateBackgroundColor else Color.TRANSPARENT)

//            itemView.setOnClickListener {
//                val previousPosition = selectedPosition
//                selectedPosition = adapterPosition
//
//                if (previousPosition != RecyclerView.NO_POSITION) {
//                    notifyItemChanged(previousPosition)
//                }
//                notifyItemChanged(selectedPosition)
//            }

            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition

                if (previousPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousPosition)
                }
                notifyItemChanged(selectedPosition)

                onDateSelected?.invoke(date) // Invoke the callback with the selected date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_calender, parent, false)
        return DateViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dates[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = dates.size
}

