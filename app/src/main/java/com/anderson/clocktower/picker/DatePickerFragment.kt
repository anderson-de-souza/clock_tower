package com.anderson.clocktower.picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

class DatePickerFragment(var listener: DatePickerDialog.OnDateSetListener): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val date = LocalDate.now()

        return DatePickerDialog(
            activity as Context, listener,
            date.year, date.monthValue, date.dayOfMonth
        )

    }

}