package com.anderson.clocktower

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anderson.clocktower.databinding.ActivityMainBinding
import com.anderson.clocktower.livedata.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fragmentContainerView.apply {

        }

        binding.buttonAddAlarm.setOnClickListener {
            var intent = Intent(this@MainActivity, AlarmActivity::class.java)
            startActivity(intent)
        }

    }

}