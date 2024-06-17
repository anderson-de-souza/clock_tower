package com.anderson.clocktower.fragment;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.anderson.clocktower.R
import com.anderson.clocktower.adapter.ExactAlarmAdapter
import com.anderson.clocktower.databinding.FragmentAlarmHostBinding
import com.anderson.clocktower.databinding.FragmentExactAlarmListBinding
import com.google.android.material.tabs.TabLayout

class AlarmHostFragment: Fragment(R.layout.fragment_alarm_host) {

    private var binding: FragmentAlarmHostBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?: {
            binding = FragmentAlarmHostBinding.bind(view)
        }

        binding!!.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.id) {
                    R.id.tab_all_alarms -> binding!!.viewPager.currentItem = 0
                    R.id.tab_exact_alarms -> binding!!.viewPager.currentItem = 1
                    R.id.tab_repeat_alarms -> binding!!.viewPager.currentItem = 2
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        });

        val fragmentAdapter = AlarmViewPagerAdapter(activity as FragmentActivity)
        binding!!.viewPager.adapter = fragmentAdapter

    }

    class AlarmViewPagerAdapter(val activity: FragmentActivity): FragmentStateAdapter(activity) {

        private val fragments: List<Fragment> = listOf(
            AllAlarmFragment(), ExactAlarmFragment(), RepeatAlarmFragment()
        )

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments.get(position)
        }

    }

}
