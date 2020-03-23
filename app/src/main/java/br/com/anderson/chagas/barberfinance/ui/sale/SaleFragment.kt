package br.com.anderson.chagas.barberfinance.ui.sale

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.model.Sale
import kotlinx.android.synthetic.main.fragment_sale.*
import java.math.BigDecimal

class SaleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = recycler_list_sales
        recyclerView.adapter =
            SaleListAdapter(
                sale(),
                activity
            )
    }

    private fun sale(): List<Sale>{
        return listOf(
                Sale("Junior", "8:50 AM", BigDecimal(25.00), "Dinheiro"),
                Sale("Junior", "9:50 AM", BigDecimal(50.00), "Crédito"),
                Sale("Fernando", "10:50 AM", BigDecimal(25.00), "Fiado"),
                Sale("junior", "11:50 AM", BigDecimal(20.00), "Dinheiro"),
                Sale("Fernando", "14:00 PM", BigDecimal(25.00), "Crédito"),
                Sale("junior", "15:09 PM", BigDecimal(30.00), "Crédito"),
                Sale("Fernando", "17:03 PM", BigDecimal(25.00), "Dinheiro")
            )
    }
}