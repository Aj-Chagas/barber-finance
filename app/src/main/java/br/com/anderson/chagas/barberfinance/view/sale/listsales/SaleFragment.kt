package br.com.anderson.chagas.barberfinance.view.sale.listsales

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.formatsDateForBrazilian
import kotlinx.android.synthetic.main.fragment_sale.*
import org.koin.android.ext.android.inject
import java.util.*


class SaleFragment : Fragment() {

    private val adapter by lazy {
        SaleListAdapter(context = activity)
    }

    private val viewModel by inject<SaleListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setAdapter()
        getSaleList()
    }

    private fun getSaleList() {
        viewModel.getSaleList().observe(viewLifecycleOwner, Observer { sales ->
            adapter.update(sales)
        })
    }

    private fun setAdapter() {
        recycler_list_sales.adapter = adapter
    }


    private fun setListener() {
        add_sale.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_navigation_sales_to_navigation_method_payment,
                null
            )
        )
    }

}