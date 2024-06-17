package com.anderson.clocktower.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anderson.clocktower.R
import com.anderson.clocktower.databinding.ViewHolderDayOfWeekBinding

class DayOfWeekAdapter(): RecyclerView.Adapter<DayOfWeekAdapter.DayOfWeekViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun getItemCount(): Int {
        return 7
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayOfWeekViewHolder {

        inflater?: run {
            inflater = LayoutInflater.from(parent.context)
        }

        var binding = ViewHolderDayOfWeekBinding.inflate(inflater!!, parent, false)

        return DayOfWeekViewHolder(binding)

    }

    override fun onBindViewHolder(holder: DayOfWeekViewHolder, position: Int) {
        holder.onBind(position)
    }

    class DayOfWeekViewHolder(val binding: ViewHolderDayOfWeekBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val dayName = binding.root.context.getString(getResId(position))
            binding.buttonDay.text = dayName
        }

        fun getResId(index: Int): Int =
            when (index) {
                0 -> R.string.sunday
                1 -> R.string.monday
                2 -> R.string.tuesday
                3 -> R.string.wednesday
                4 -> R.string.thursday
                5 -> R.string.friday
                6 -> R.string.saturday
                else -> -1
            }

    }

}

