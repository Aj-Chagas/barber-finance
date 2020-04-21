package br.com.anderson.chagas.barberfinance.view.conclusion

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.setActionbarTitle
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.fragment_concluded.*


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
        setAnimation(view)
    }

    private fun setAnimation(view: View) {
        val animationView = progressBar as LottieAnimationView
        animationView.speed = 1.90f
        animationView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                goToNavigationSales(view)
            }
        })
    }

    private fun goToNavigationSales(view: View) {
        view.findNavController().navigate(R.id.action_navigation_concluded_to_navigation_sales)
    }
}