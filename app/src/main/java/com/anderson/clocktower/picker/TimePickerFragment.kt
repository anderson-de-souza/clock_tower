package com.anderson.clocktower.picker

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import java.time.LocalTime

class TimePickerFragment(val listener: TimePickerDialog.OnTimeSetListener): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val datetime = LocalTime.now()

        return TimePickerDialog(
            activity, listener, datetime.hour, datetime.minute,
            DateFormat.is24HourFormat(activity)
        )

    }

}