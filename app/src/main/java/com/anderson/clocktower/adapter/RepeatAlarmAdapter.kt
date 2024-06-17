package com.anderson.clocktower.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anderson.clocktower.databinding.ViewholderRepeatAlarmBinding
import com.anderson.clocktower.schema.RepeatAlarm

class RepeatAlarmAdapter(): ListAdapter<RepeatAlarm, RepeatAlarmAdapter.RepeatAlarmViewHolder>(
    object: DiffUtil.ItemCallback<RepeatAlarm>() {

        override fun areItemsTheSame(oldItem: RepeatAlarm, newItem: RepeatAlarm): Boolean {
            return oldItem === oldItem
        }

        override fun areContentsTheSame(oldItem: RepeatAlarm, newItem: RepeatAlarm): Boolean {
            return oldItem.equals(newItem)
        }

    }
) {

    var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatAlarmViewHolder {
        inflater?: {
            inflater = LayoutInflater.from(parent.context)
        }
        val holder = RepeatAlarmViewHolder(ViewholderRepeatAlarmBinding.inflate(inflater!!, parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: RepeatAlarmViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RepeatAlarmViewHolder(val binding: ViewholderRepeatAlarmBinding): RecyclerView.ViewHolder(binding.root) {

        fun onBind(repeatAlarm: RepeatAlarm) {
            binding.viewTitle.text = repeatAlarm.alarm.name
            binding.viewDescription.text = repeatAlarm.alarm.description
            binding.viewSongPath.text = repeatAlarm.alarm.songUri
            binding.viewImage.setImageURI(Uri.parse(repeatAlarm.alarm.imageUri))
        }

    }

}