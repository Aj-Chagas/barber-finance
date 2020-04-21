package br.com.anderson.chagas.barberfinance.view.result

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.input_date_picker_dialog.view.*
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.collections.ArrayList

class ResultFragment : Fragment() {

    private val viewModel by inject<ResultViewModel>()
    private val calendar = Calendar.getInstance()
    private var total : Double? = null

    //Color.rgb(246,75,80) vermelho
    //Color.rgb(97,84,174) roxo
    //Color.rgb(246,169,94) amarelo
    //Color.rgb(0,153,155) verde

    private val colorArray: MutableList<Int> = mutableListOf(Color.rgb(97,84,174), Color.rgb(0,153,155), Color.rgb(246,75,80))
    private var dataVals = ArrayList<PieEntry>(listOf(PieEntry(0f, ""), PieEntry(0f, ""), PieEntry(0f, "")))

    companion object {
        private const val MONEY = "money"
        private const val CREDITCARD = "creditCard"
        private const val INSTALLMENT = "installment"
        private const val ALERT_MSG = "Não houve vendas nessa data "
        private const val DINHEIRO = "Dinheiro"
        private const val FIADO = "Fiado"
        private const val CREDITO = "Crédito"
        private const val ZEROED_VALUE = ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeTextView()
        setupListener()
        setupPieChart()
    }

    private fun setupListener() {
        results_chage_data.setOnClickListener {
            showNoticeDialog()
        }
    }

    private fun setupPieChart() {
        val pieDataSet = PieDataSet(dataVals, "")
        pieDataSet.colors = colorArray
        val pieData = PieData(pieDataSet)
        pieData.setValueFormatter(PercentFormatter(pieChart))
        pieData.setValueTextSize(12f)
        pieData.setValueTextColor(Color.WHITE)
        pieChart.data = pieData
        pieChart.centerText = total?.toBigDecimal()?.formatsCurrencyForBrazilian()
        pieChart.setCenterTextSize(20f)
        pieChart.setEntryLabelTextSize(0f)
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.legend.isEnabled = true
        pieChart.description.isEnabled = false
        pieChart.setUsePercentValues(true)
        pieChart.legend.textSize = 12f
        pieChart.invalidate()
    }

    private fun initializeTextView() {
        viewModel.getTotal().observe(viewLifecycleOwner, Observer { total ->
            if(total.toDouble() == 0.0){
                results_textview_msg.text = ALERT_MSG
                results_textview_msg.visibility = View.VISIBLE
            } else{
                results_textview_msg.visibility = View.GONE
            }
            this.total = total.toDouble()
        })

        viewModel.getTotalMoney().observe(viewLifecycleOwner, Observer {
            results_textview_money.text = it.formatsCurrencyForBrazilian()
            updateProgressByType(it.toDouble(), MONEY)
        })

        viewModel.getTotalCredidCard().observe(viewLifecycleOwner, Observer {
            results_textview_credid_card.text = it.formatsCurrencyForBrazilian()
            updateProgressByType(it.toDouble(), CREDITCARD)
        })

        viewModel.getTotalInstallment().observe(viewLifecycleOwner, Observer {
            results_textview_installment.text = it.formatsCurrencyForBrazilian()
            updateProgressByType(it.toDouble(), INSTALLMENT)
        })

        viewModel.getTotalFernando().observe(viewLifecycleOwner, Observer {
            results_fernando.text = it.formatsCurrencyForBrazilian()
        })

        viewModel.getTotalJunior().observe(viewLifecycleOwner, Observer {
            results_junior.text = it.formatsCurrencyForBrazilian()
        })

        results_chage_data.text = calendar.formatsDateForBrazilian()
    }

    private fun updateProgressByType(value: Double, type : String) {
        total?.let {
            when(type){
                MONEY -> {
                    val progress = viewModel.calculeteProgressPieChart(value, it)
                    dataVals.removeAt(0)
                    dataVals.add(0, PieEntry(progress.toFloat(), DINHEIRO))
                    setupPieChart()
                }
                INSTALLMENT -> {
                    val progress = viewModel.calculeteProgressPieChart(value, it)
                    dataVals.removeAt(1)
                    dataVals.add(1, PieEntry(progress.toFloat(), FIADO))
                    setupPieChart()
                }
                CREDITCARD -> {
                    val progress = viewModel.calculeteProgressPieChart(value, it)
                    dataVals.removeAt(2)
                    dataVals.add(2, PieEntry(progress.toFloat(), CREDITO))
                    setupPieChart()
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