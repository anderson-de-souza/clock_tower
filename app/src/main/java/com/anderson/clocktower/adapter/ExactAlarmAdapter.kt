package com.anderson.clocktower.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anderson.clocktower.databinding.ViewholderExactAlarmBinding
import com.anderson.clocktower.schema.ExactAlarm

class ExactAlarmAdapter(): ListAdapter<ExactAlarm, ExactAlarmAdapter.ExactAlarmViewHolder>(
    object: DiffUtil.ItemCallback<ExactAlarm>() {

        override fun areItemsTheSame(oldItem: ExactAlarm, newItem: ExactAlarm): Boolean {
            return oldItem === oldItem
        }

        override fun areContentsTheSame(oldItem: ExactAlarm, newItem: ExactAlarm): Boolean {
            return oldItem.equals(newItem)
        }

    }
) {

    var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExactAlarmViewHolder {
        inflater?: {
            inflater = LayoutInflater.from(parent.context)
        }
        val holder = ExactAlarmViewHolder(ViewholderExactAlarmBinding.inflate(inflater!!, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: ExactAlarmViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ExactAlarmViewHolder(val binding: ViewholderExactAlarmBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(exactAlarm: ExactAlarm) {
            binding.viewTitle.text = exactAlarm.alarm.name
            binding.viewDescription.text = exactAlarm.alarm.description
            binding.viewSongPath.text = exactAlarm.alarm.songUri
            binding.viewImage.setImageURI(Uri.parse(exactAlarm.alarm.imageUri))
        }

    }

}