package com.pramod.horizontal_calender

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.pramod.horizontalcalendarview.R
import java.text.SimpleDateFormat
import java.util.*

class DateAdapter(
    private val context: Context,
    private var dates: List<Date> = emptyList(),
    private val onDateSelected: ((Date) -> Unit)? = null
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    private var selectedPosition = RecyclerView.NO_POSITION // No position selected initially

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.date)

        fun bind(date: Date, isSelected: Boolean) {
            dateTextView.text = dateFormat.format(date)
            val typeface = ResourcesCompat.getFont(context, R.font.roboto)

            // Apply selection styles
            if (isSelected) {
                dateTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryVariant))
                dateTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
                dateTextView.setTypeface(typeface, Typeface.BOLD)
            } else {
                // Reset to default styles
                dateTextView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
                dateTextView.setTextColor(ContextCompat.getColor(context, R.color.black))
                dateTextView.setTypeface(typeface, Typeface.NORMAL)
            }

            itemView.setOnClickListener {
                // Update the selected position
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition

                // Notify the adapter to refresh views
                if (previousPosition != RecyclerView.NO_POSITION) {
                    notifyItemChanged(previousPosition)
                }
                notifyItemChanged(selectedPosition)

                // Trigger the callback if set
                onDateSelected?.invoke(date)
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

    fun clearSelection() {
        val previousPosition = selectedPosition
        if (previousPosition != RecyclerView.NO_POSITION) {
            selectedPosition = RecyclerView.NO_POSITION
            notifyItemChanged(previousPosition) // Update the previously selected item
        }
    }

    override fun getItemCount(): Int = dates.size
}
