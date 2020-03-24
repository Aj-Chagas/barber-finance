package br.com.anderson.chagas.barberfinance.ui.sale.listsales

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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
        setupAdapterRecyclerView()

        add_sale.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_navigation_sales_to_navigation_method_payment, null)
        )
    }

    private fun setupAdapterRecyclerView() {
        val recyclerView = recycler_list_sales
        recyclerView.adapter = SaleListAdapter(context = activity)
    }

}