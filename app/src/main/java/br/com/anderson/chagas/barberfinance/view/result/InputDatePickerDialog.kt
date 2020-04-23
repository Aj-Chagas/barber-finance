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

    private val c : Calendar = Calendar.getInstance()
    private lateinit var inflatedView: View
    private val datePickerFragment by lazy {
        DatePickerFragment(context, inflatedView)
    }

    companion object {
        private const val OK = "ok"
        private const val CANCEL = "cancelar"
    }


    fun showInputDialog(
        positiveAction : (inflatedView : View) -> Unit = {},
        negativeAction : (dialog : DialogInterface) -> Unit = {}
    )   {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)

        inflatedView = inflater.inflate(R.layout.input_date_picker_dialog, null)
        builder.setView(inflatedView)
            .setPositiveButton(OK) { _, _ ->
                positiveAction(inflatedView)
            }
            .setNegativeButton(CANCEL) { dialog, _ ->
                negativeAction(dialog)
            }
        builder
            .create()
            .show()

        onCreateDialog()

    }

    private fun onCreateDialog(){
        initializeInputs()

        configureListener()

    }


    private fun initializeInputs() {
        val formatsDateForBrazilian = c.formatsDateForBrazilian()
        val formatDateddMMMMyyyy = c.formatDateddMMMMyyyy()
        //inflatedView.dialog_date_edittext_date_start?.setText(formatsDateForBrazilian)
        inflatedView.dialog_date_textview_date?.text = formatDateddMMMMyyyy
    }

    private fun configureListener() {
        inflatedView.dialog_date_edittext_date_start.setOnClickListener {
            datePickerFragment.showDataPicker().show()
            datePickerFragment.dateSelected = this::bindDateStart
        }

        inflatedView?.dialog_date_edittext_date_final.setOnClickListener {
            datePickerFragment.showDataPicker().show()
            datePickerFragment.dateSelected = this::bindDateFinal
        }
    }

    private fun bindDateStart(date : Calendar) {
        inflatedView?.dialog_date_edittext_date_start?.setText(date.formatsDateForBrazilian())
    }

    private fun bindDateFinal(date : Calendar) {
        inflatedView?.dialog_date_edittext_date_final?.setText(date.formatsDateForBrazilian())
    }

}








