package br.com.anderson.chagas.barberfinance.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.extension.MoneyTextWatcher
import br.com.anderson.chagas.barberfinance.extension.showError
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_service_cost.*
import java.util.*


class ServiceCost : Fragment() {

    private lateinit var chipChecked : Chip

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_service_cost, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestFocusInEditTextValueMoney()
        setListenerButtonConcludes(getBundleMethodPayment())
        setupChip()
        setTextWatchValueMoney()
    }

    private fun setTextWatchValueMoney() {
        value_money.addTextChangedListener(MoneyTextWatcher(value_money, getLocale()))
    }

    private fun getLocale(): Locale {
        return Locale("pt", "BR")
    }

    private fun setupChip() {

        chip_fernando.setOnClickListener {
            chip_fernando.isCheckable = true
            chip_fernando.isChecked = true
            chip_junior.isChecked = false
            chip_junior.isCheckable = false
        }

        chip_junior.setOnClickListener {
            chip_junior.isCheckable = true
            chip_junior.isChecked = true
            chip_fernando.isChecked = false
            chip_fernando.isCheckable = false
        }
    }

    private fun getBundleMethodPayment(): String? {
        return arguments?.getString(getString(R.string.method_payment_key))
    }

    private fun setListenerButtonConcludes(methodPayment: String?) {
        button_concludes.setOnClickListener {

            if(verifyData()){
                val saleBundle = getBundle(methodPayment, verifyWhichChipIsChecked())
                goToConcludedFragment(it, saleBundle)
                closeKeyboard()
            }
        }
    }


    private fun verifyData(): Boolean {
        if(verifyWhichChipIsChecked() == null){
            return false
        } else if(value_money.text.isNullOrEmpty()){
            showError("Digite o valor da venda.")
            return false
        }
        return true
    }

    private fun verifyWhichChipIsChecked(): Chip? {
        return when {
            chip_junior.isChecked -> {
                chip_junior
            }
            chip_fernando.isChecked -> {
                chip_fernando
            }
            else -> {
                showError("Selecione o barbeiro")
                null
            }
        }
    }

    private fun getBundle(
        methodPayment: String?,
        chipChecked: Chip?
    ): Bundle {
        return Bundle().apply {
            putString(getString(R.string.method_payment_key), methodPayment)
            putString(getString(R.string.service_cost_key), value_money.text.toString())
            putString(getString(R.string.barber_key), chipChecked?.text.toString())
        }
    }

    private fun goToConcludedFragment(it: View, saleBundle: Bundle) {
        it.findNavController()
            .navigate(R.id.action_navigation_service_cost_to_concludedFragment, saleBundle)
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