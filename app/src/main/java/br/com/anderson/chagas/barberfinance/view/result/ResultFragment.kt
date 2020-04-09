package br.com.anderson.chagas.barberfinance.view.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.anderson.chagas.barberfinance.R
import kotlinx.android.synthetic.main.fragment_result.*
import org.koin.android.ext.android.inject

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

}