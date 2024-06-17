package com.anderson.clocktower.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anderson.clocktower.R
import com.anderson.clocktower.adapter.RepeatAlarmAdapter
import com.anderson.clocktower.databinding.FragmentRepeatAlarmListBinding

class RepeatAlarmFragment: Fragment(R.layout.fragment_repeat_alarm_list) {

    private var binding: FragmentRepeatAlarmListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?: {
            binding = FragmentRepeatAlarmListBinding.bind(view)
        }

        var alarmAdapter = RepeatAlarmAdapter()

        binding!!.listView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = alarmAdapter
        }

    }

}