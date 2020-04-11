package br.com.anderson.chagas.barberfinance.view.result

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.DatePicker
import br.com.anderson.chagas.barberfinance.app.extension.formatDateddMMMMyyyy
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import kotlinx.android.synthetic.main.input_date_picker_dialog.view.*
import java.util.*

class DatePickerFragment(val context: Context,
                         val inflatedView: View?) :  DatePickerDialog.OnDateSetListener {

    val c = Calendar.getInstance()

    fun showDataPicker(): DatePickerDialog {
        // Use the current date as the default date in the picker
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(context, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

        c.set(year, month, day)
        val formatsDateForBrazilian = c.formatsDateForBrazilian()
        val formatDateddMMMMyyyy = c.formatDateddMMMMyyyy()
        inflatedView?.dialog_date_edittext_date?.setText(formatsDateForBrazilian)
        inflatedView?.dialog_date_textview_date?.setText(formatDateddMMMMyyyy)
    }
}