package br.com.anderson.chagas.barberfinance.view.result

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.formatDateddMMMMyyyy
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import kotlinx.android.synthetic.main.input_date_picker_dialog.view.*
import java.util.*

class InputDatePickerDialog(private val context: Context) {

    val c : Calendar = Calendar.getInstance()
    private lateinit var inflatedView: View


    fun showInputDialog(
        positiveAction : (inflatedView : View) -> Unit = {},
        negativeAction : (dialog : DialogInterface) -> Unit = {}
    )   {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)

        inflatedView = inflater.inflate(R.layout.input_date_picker_dialog, null)
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
        InitializeInputs()
        configureListener()
    }

    private fun InitializeInputs() {
        val formatsDateForBrazilian = c.formatsDateForBrazilian()
        val formatDateddMMMMyyyy = c.formatDateddMMMMyyyy()
        inflatedView.dialog_date_edittext_date?.setText(formatsDateForBrazilian)
        inflatedView.dialog_date_textview_date?.text = formatDateddMMMMyyyy
    }

    private fun configureListener() {
        inflatedView.dialog_date_edittext_date.setOnClickListener {
            val datePickerFragment = DatePickerFragment(context, inflatedView)
            datePickerFragment.showDataPicker().show()
        }
    }

}








