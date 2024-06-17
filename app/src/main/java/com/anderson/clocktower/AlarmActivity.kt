package com.anderson.clocktower;

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anderson.clocktower.adapter.DayOfWeekAdapter
import com.anderson.clocktower.database.RealDatabase
import com.anderson.clocktower.databinding.ActivityAlarmBinding
import com.anderson.clocktower.livedata.MainViewModel
import com.anderson.clocktower.picker.DatePickerFragment
import com.anderson.clocktower.picker.TimePickerFragment
import java.time.LocalTime
import java.util.Calendar
import java.util.Date

class AlarmActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAlarmBinding

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private lateinit var menu: Menu

    private var alarmName: String? = null
        get() = binding.fieldAlarmName.editText?.text.toString()

    private var alarmDescription: String? = null
        get() = binding.fieldAlarmDescription.editText?.text.toString()

    private var alarmSongUri: Uri? = null

    private var alarmImageUri: Uri? = null

    private lateinit var baseCalendar: Calendar

    private val datePickerCalendar: Calendar by lazy {
        Calendar.getInstance()
    }

    private val timePickerCalendar: Calendar by lazy {
        Calendar.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityAlarmBinding>(this, R.layout.activity_alarm)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        baseCalendar = Calendar.getInstance()
        updatePlaceHolders()

        binding.recyclerViewDayOfWeek.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                this@AlarmActivity, LinearLayoutManager.HORIZONTAL, false
            )
            adapter = DayOfWeekAdapter()
        }

        binding.placeholderDate.setOnClickListener {

            DatePickerFragment(object: DatePickerDialog.OnDateSetListener {

                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    datePickerCalendar.apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }

                    updatePlaceHolders()

                }

            }).show(supportFragmentManager, "setDate")

        }

        binding.placeholderTime.setOnClickListener {
            TimePickerFragment(object: TimePickerDialog.OnTimeSetListener {

                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                    timePickerCalendar.apply{
                        set(Calendar.HOUR_OF_DAY, hourOfDay)
                        set(Calendar.MINUTE, minute)
                    }

                    updatePlaceHolders()

                }

            }).show(supportFragmentManager, "setTime")
        }

        val songPickerLauncher = registerForActivityResult(GetContent()) {
            alarmSongUri = it
            MediaPlayer.create(this, it).apply {
                setOnPreparedListener {
                    it.start()
                    Handler(Looper.getMainLooper()).postDelayed(
                        { it.stop() }, 10 * 1000
                    )
                }
                prepare()
            }
        }

        binding.buttonChangeSong.setOnClickListener {
            songPickerLauncher.launch("audio/mp3")
        }

        val imagePickerLauncher = registerForActivityResult(GetContent()) {
            binding.viewAlarmImage.setImageURI(it)
            alarmImageUri = it
        }

        binding.viewAlarmImage.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu!!
        menuInflater.inflate(R.menu.activity_alarm_toolbar, menu)
        binding.isRepeat = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.item_reset) {
            recreate()
            return true
        }

        if (item.itemId == R.id.item_repeat_alarm || item.itemId == R.id.item_exact_alarm) {
            binding.isRepeat = !(item.itemId == R.id.item_repeat_alarm)
            toggleAlarmTypeItem(item)
        }

        return super.onOptionsItemSelected(item)

    }

    fun toggleAlarmTypeItem(item: MenuItem) {
        menu.findItem(R.id.item_exact_alarm).apply {
            setVisible(!(item.itemId == itemId))
        }
        menu.findItem(R.id.item_repeat_alarm).apply {
            setVisible(!(item.itemId == itemId))
        }
    }

    fun updatePlaceHolders() {

        binding.apply {

            placeholderDate.text = DateFormat.getDateFormat(this@AlarmActivity)
                .format(datePickerCalendar.time)

            placeholderTime.text = DateFormat.getTimeFormat(this@AlarmActivity)
                .format(timePickerCalendar.time)

        }

    }

    fun generateAlarm() {

    }

    fun compareTime(): Boolean {
        return baseCalendar.get(Calendar.HOUR_OF_DAY) == timePickerCalendar.get(Calendar.HOUR_OF_DAY)
            && baseCalendar.get(Calendar.MINUTE) == timePickerCalendar.get(Calendar.MINUTE)
    }

    fun compareDate(): Boolean {
        return baseCalendar.get(Calendar.MONTH) == datePickerCalendar.get(Calendar.MONTH)
            && baseCalendar.get(Calendar.DAY_OF_MONTH) == datePickerCalendar.get(Calendar.DAY_OF_MONTH)
                && baseCalendar.get(Calendar.YEAR) == datePickerCalendar.get(Calendar.YEAR)
    }

}
