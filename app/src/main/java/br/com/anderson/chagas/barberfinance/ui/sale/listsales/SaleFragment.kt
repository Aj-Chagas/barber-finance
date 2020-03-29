package br.com.anderson.chagas.barberfinance.ui.sale.listsales

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.model.Sale
import kotlinx.android.synthetic.main.fragment_sale.*
import kotlinx.coroutines.newFixedThreadPoolContext
import org.koin.android.ext.android.inject


class SaleFragment : Fragment() {

/*    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SaleListViewModel::class.java)
    }*/

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

        viewModel.getPeopleList().observe(viewLifecycleOwner, Observer { sales ->
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