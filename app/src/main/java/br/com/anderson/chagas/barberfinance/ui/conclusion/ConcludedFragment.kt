package br.com.anderson.chagas.barberfinance.ui.conclusion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.anderson.chagas.barberfinance.R


class ConcludedFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_concluded, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wrapperBundle()


/*        val animationView = progressBar as LottieAnimationView
        animationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.findNavController().navigate(R.id.action_navigation_concluded_to_navigation_sales)
            }
        })*/

    }

    private fun wrapperBundle() {
        val methodPayment = arguments?.getString(getString(R.string.method_payment_key))
        val valueMoney = arguments?.getString(getString(R.string.service_cost_key))
        val barber = arguments?.getString(getString(R.string.barber_key))
    }

}