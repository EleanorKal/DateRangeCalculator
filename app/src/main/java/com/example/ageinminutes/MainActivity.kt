package com.example.daterangecalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.util.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.btnDatePicker) as Button
        // to test that the button works
        button.setOnClickListener { view ->
            clickDatePicker(view)
            // Toast.makeText(this, "Button works", Toast.LENGTH_LONG).show()
        }

    }


    //To open a calendar
    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val tViewSelectedDate = findViewById(R.id.tvSelectedDate) as TextView
        val tVShowAgeInMinutes = findViewById(R.id.tvShowAgeInMinutes) as TextView
        //calendar building code
        //    Toast.makeText(this, "Date Picker works", Toast.LENGTH_LONG).show()
        //    --> Toast to show that the DatePicker works
        //      Toast.makeText(this, "The chosen year is " + year, Toast.LENGTH_LONG).show()
        // Toast.makeText(this, "The chosen year is $selectedYear,
        // the month is $selectedMonth and the day is $selectedDayOfMonth",
        // Toast.LENGTH_LONG).show()

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"

            tViewSelectedDate.setText(selectedDate)

            // now to format the date
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            // the parse method converts this String object to a Date object
            val theDate = sdf.parse(selectedDate)

            // here, you are using theDate val and evaluating the time in minutes
            // be careful with the force unwrap !! this should only be used if you're certain
            // that a null will not be returned
            // Note millisecond is only available from 1st January 1970
            val selectedDateInMinutes = theDate!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = (currentDate!!.time / 60000)

            // to calculate the difference between the current date in minutes and your birth date
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

           // display the difference in minutes. Assign the differenceInMinutes object to
           // the tVShowAgeInMinutes text view
           tVShowAgeInMinutes.setText(differenceInMinutes.toString())

        }, year, month, day)


        // Ensure that only past dates can be selected
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

        // End of calendar building code
    }



}



