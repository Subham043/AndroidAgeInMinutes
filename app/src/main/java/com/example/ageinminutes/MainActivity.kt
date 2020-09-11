package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btndate.setOnClickListener{view -> clickDatePicker(view)  }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val Year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

            val selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateinMinute = theDate!!.time/86400000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time/86400000
            val differenceToMinutes = currentDateInMinutes - selectedDateinMinute
            tvDateInMinute.setText(differenceToMinutes.toString())
        },Year,month,day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}