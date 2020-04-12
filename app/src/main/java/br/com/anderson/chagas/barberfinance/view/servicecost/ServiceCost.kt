package br.com.anderson.chagas.barberfinance.view.servicecost

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.MoneyTextWatcher
import br.com.anderson.chagas.barberfinance.app.extension.setActionbarTitle
import br.com.anderson.chagas.barberfinance.app.extension.showMsg
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_service_cost.*
import org.koin.android.ext.android.inject
import java.util.*


class ServiceCost : Fragment() {

    private val viewModel by inject<ServiceCostViewModel>()
    private val paymentMethod by lazy {
        arguments?.getString(getString(R.string.method_payment_key))
    }

    companion object {
        private const val SELECT_THE_BARBER = "Selecione o barbeiro"
        private const val ENTER_THE_VALUE_OF_SALE = "Digite o valor da venda"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_service_cost, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setActionbarTitle(R.string.service_cost, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestFocusInEditTextValueMoney()
        setListenerButtonConcludes()
        setupChipListeners()
        setTextWatchValueMoney()
    }


    private fun setTextWatchValueMoney() {
        value_money.addTextChangedListener(MoneyTextWatcher(value_money, Locale("pt", "BR")))
    }

    private fun setupChipListeners() {
        chip_fernando.setOnClickListener {
            enableChipSelectedAndUnableChipUnselected(chip_fernando, chip_junior)
            viewModel.chipSelected(chip_fernando)
        }

        chip_junior.setOnClickListener {
            enableChipSelectedAndUnableChipUnselected(chip_junior, chip_fernando)
            viewModel.chipSelected(chip_junior)
        }
    }

    private fun enableChipSelectedAndUnableChipUnselected(
        chipSelected: Chip,
        chipUnselected: Chip
    ) {
        chipSelected.isCheckable = true
        chipSelected.isChecked = true
        chipUnselected.isChecked = false
        chipUnselected.isCheckable = false
    }

    private fun setListenerButtonConcludes() {
        button_concludes.setOnClickListener {
            if(!viewModel.checkChipIsSelected()){
                showMsg(SELECT_THE_BARBER)
            } else if(!viewModel.checkValueMoneyIsNotEmpty(value_money.text.toString())){
                showMsg(ENTER_THE_VALUE_OF_SALE)
            }else {
                viewModel.saveSale(paymentMethod)
                goToConcludedFragment()
                closeKeyboard()
            }
        }
    }

    private fun goToConcludedFragment() {
        this.findNavController()
            .navigate(R.id.action_navigation_service_cost_to_concludedFragment)
    }

    private fun openAndHideKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun closeKeyboard(){
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, 0)
    }

    private fun requestFocusInEditTextValueMoney() {
        value_money.isFocusableInTouchMode = true
        value_money.requestFocus()
        openAndHideKeyboard()
    }

    override fun onStop() {
        super.onStop()
        closeKeyboard()
    }
}