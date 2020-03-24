package br.com.anderson.chagas.barberfinance.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import br.com.anderson.chagas.barberfinance.R
import kotlinx.android.synthetic.main.fragment_method_payment.*



class MethodPayment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_method_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners(view)
    }

    private fun setListeners(view: View) {
        cardview_credit_card.setOnClickListener {
            goToServiceCost(
                view,
                getString(R.string.credi_card)
            )
        }
        cardView_installment_sale.setOnClickListener {
            goToServiceCost(
                view,
                getString(R.string.installment_sale)
            )
        }
        cardView_money.setOnClickListener { goToServiceCost(view, getString(R.string.money)) }
    }

    private fun goToServiceCost(view: View, methodPayment: String) {
        val saleBundle = Bundle().apply {
            putString(getString(R.string.method_payment_key), methodPayment)
        }
        view.findNavController()
            .navigate(R.id.action_navigation_method_payment_to_serviceCost, saleBundle)
    }

}