package br.com.anderson.chagas.barberfinance.ui.conclusion

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.model.Sale
import br.com.anderson.chagas.barberfinance.ui.sale.listsales.SaleListViewModel
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.fragment_concluded.*
import org.koin.android.ext.android.inject
import java.math.BigDecimal
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ConcludedFragment : Fragment() {

    private val viewModel by inject<ConclusionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_concluded, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val animationView = progressBar as LottieAnimationView
        animationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                wrapperBundle()
                view.findNavController().navigate(R.id.action_navigation_concluded_to_navigation_sales)
            }
        })

    }

    private fun wrapperBundle() {
        val methodPayment = arguments?.getString(getString(R.string.method_payment_key))
        val valueMoney = arguments?.getString(getString(R.string.service_cost_key))
        val barber = arguments?.getString(getString(R.string.barber_key))


        val dateFormat: DateFormat = SimpleDateFormat("hh:mm a")
        val rightNow = Date()
        val dateFormated = dateFormat.format(rightNow)

        val valueMoneyTeste = valueMoney?.replace("R$", "")?.replace(",", ".")?.trim()

        val bigDecimal = valueMoneyTeste!!.toBigDecimal()

        Log.i("teste", valueMoneyTeste + bigDecimal.toString())

        viewModel.addSale(Sale(barberName = barber.toString(),
            salePrice = valueMoneyTeste.toBigDecimal(),
            paymentMethod = methodPayment.toString(),
            creationTime = dateFormated
        ))

        Log.i("teste", methodPayment.toString() + " " + valueMoney.toString() + " " + barber.toString() + dateFormated.toString())
    }

}