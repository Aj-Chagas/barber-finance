package br.com.anderson.chagas.barberfinance.ui.sale.listsales

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.Navigation
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.model.Sale
import kotlinx.android.synthetic.main.fragment_sale.*
import org.koin.android.ext.android.inject


class SaleFragment : Fragment() {

/*    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SaleListViewModel::class.java)
    }*/

    private val recyclerView by lazy {
        recycler_list_sales
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
        viewModel.getPeopleList().observe(viewLifecycleOwner, Observer { sales ->
            sales.let { salesList ->
                setupAdapterRecyclerView(salesList)
            }
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        ViewModelProviders.of(this).get(SaleListViewModel::class.java)



         //viewModel = ViewModelProviders.of(this, ViewModelProvider.AndroidViewModelFactory(getApplication())).get(SaleListViewModel::class.java)
    }

    private fun setListener() {
        add_sale.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_navigation_sales_to_navigation_method_payment,
                null
            )
        )
    }

    private fun setupAdapterRecyclerView(salesList: List<Sale>) {
        recyclerView.adapter = SaleListAdapter(sales = salesList, context = activity)
    }

}