package com.anderson.clocktower.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anderson.clocktower.R
import com.anderson.clocktower.adapter.ExactAlarmAdapter
import com.anderson.clocktower.databinding.FragmentAllAlarmListBinding

class AllAlarmFragment: Fragment(R.layout.fragment_all_alarm_list) {

    private var binding: FragmentAllAlarmListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?: {
            binding = FragmentAllAlarmListBinding.bind(view)
        }

        var alarmAdapter = ExactAlarmAdapter()

        binding!!.listView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = alarmAdapter
        }

    }

}
