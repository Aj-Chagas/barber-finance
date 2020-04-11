package br.com.anderson.chagas.barberfinance.view.result

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.formatDateddMMMMyyyy
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.input_date_picker_dialog.view.*
import org.koin.android.ext.android.inject
import java.util.*

@Suppress("DEPRECATION")
class ResultFragment : Fragment() {

    private val viewModel by inject<ResultViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = Date()
        viewModel.setCreationDate(date)

        initializeTextView()
        chip_today.setOnClickListener {
            showNoticeDialog()
        }

    }

    private fun initializeTextView() {
        viewModel.getTotal().observe(viewLifecycleOwner, Observer { total ->
            results_textview_total.text = total
        })

        viewModel.getTotalMoney().observe(viewLifecycleOwner, Observer {
            results_textview_money.text = it
        })

        viewModel.getTotalCredidCard().observe(viewLifecycleOwner, Observer {
            results_textview_credid_card.text = it
        })

        viewModel.getTotalInstallment().observe(viewLifecycleOwner, Observer {
            results_textview_installment.text = it
        })

        viewModel.getTotalFernando().observe(viewLifecycleOwner, Observer {
            results_fernando.text = it
        })

        viewModel.getTotalJunior().observe(viewLifecycleOwner, Observer {
            results_junior.text = it
        })
    }

    private fun showNoticeDialog() {
        val inputDatePickerDialog = InputDatePickerDialog(activity!!)
        inputDatePickerDialog.showInputDialog(
            positiveAction = this::inputDatePickerDialogPositiveAction,
            negativeAction = this::inputDatePickerDialogPositiveNegative
        )
    }

    private fun inputDatePickerDialogPositiveNegative(dialog: DialogInterface) {
        dialog.dismiss()
    }

    private fun inputDatePickerDialogPositiveAction(inflatedView: View) {
        val text = inflatedView.dialog_date_edittext_date.text
        Log.i("teste", text?.toString())
    }

}