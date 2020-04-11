package br.com.anderson.chagas.barberfinance.view.result

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import br.com.anderson.chagas.barberfinance.R
import java.util.*

class InputDatePickerDialog(
    private val context: Context
) {

    val calendar : Calendar = Calendar.getInstance()

    fun showInputDialog(
        positiveAction : (inflatedView : View) -> Unit = {},
        negativeAction : (dialog : DialogInterface) -> Unit = {}
    )   {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)

        val inflatedView = inflater.inflate(R.layout.input_date_picker_dialog, null)
        builder.setView(inflatedView)
            .setPositiveButton("Ok") { _, _ ->
                positiveAction(inflatedView)
            }
            .setNegativeButton("cancelar") { dialog, _ ->
                negativeAction(dialog)
            }
        builder
            .create()
            .show()
    }

    fun showDatePicker(){

        DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }


}








