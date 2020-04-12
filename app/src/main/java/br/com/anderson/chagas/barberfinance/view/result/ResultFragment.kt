package br.com.anderson.chagas.barberfinance.view.result

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.input_date_picker_dialog.view.*
import org.koin.android.ext.android.inject
import java.util.*

@Suppress("DEPRECATION")
class ResultFragment : Fragment() {

    private val viewModel by inject<ResultViewModel>()
    private val calendar = Calendar.getInstance()
    private var total : Double? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setCreationDate(calendar.formatsDateForBrazilian())

        initializeTextView()
        chip_today.setOnClickListener {
            showNoticeDialog()
        }

    }

    private fun initializeTextView() {
        viewModel.getTotal().observe(viewLifecycleOwner, Observer { total ->
            results_textview_total.text = total.formatsCurrencyForBrazilian()
            this.total = total.toDouble()
        })

        viewModel.getTotalMoney().observe(viewLifecycleOwner, Observer {
            results_textview_money.text = it.formatsCurrencyForBrazilian()
            updateProgressByType(it.toDouble(), "money")
        })

        viewModel.getTotalCredidCard().observe(viewLifecycleOwner, Observer {
            results_textview_credid_card.text = it.formatsCurrencyForBrazilian()
            updateProgressByType(it.toDouble(), "creditCard")
        })

        viewModel.getTotalInstallment().observe(viewLifecycleOwner, Observer {
            results_textview_installment.text = it.formatsCurrencyForBrazilian()
            updateProgressByType(it.toDouble(), "installment")
        })

        viewModel.getTotalFernando().observe(viewLifecycleOwner, Observer {
            results_fernando.text = it.formatsCurrencyForBrazilian()
        })

        viewModel.getTotalJunior().observe(viewLifecycleOwner, Observer {
            results_junior.text = it.formatsCurrencyForBrazilian()
        })
    }

    private fun updateProgressByType(value: Double, type : String) {
        total?.let {
            when(type){
                "money" -> {
                    val progress = viewModel.calculeteProgressPieChart(value, it)
                    background_progressbar_money.progress = progress
                }
                "installment" -> {
                    val progress = viewModel.calculeteProgressPieChart(value, it)
                    background_progressbar_installment.progress = progress
                }
                "creditCard" -> {
                    val progress = viewModel.calculeteProgressPieChart(value, it)
                    background_progressbar_credid_card.progress = progress
                }
            }

        }
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
        viewModel.setCreationDate(text.toString())
    }

}