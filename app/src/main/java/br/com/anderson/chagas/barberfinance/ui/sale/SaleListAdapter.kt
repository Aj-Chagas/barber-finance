package br.com.anderson.chagas.barberfinance.ui.sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.model.Sale
import kotlinx.android.synthetic.main.item_sale.view.*

class SaleListAdapter(private val sales: List<Sale>,
                      private val context: FragmentActivity?) :
    RecyclerView.Adapter<SaleListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sale, parent,false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = sales.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sale = sales[position]
        holder.bindView(sale)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindView(sale: Sale) {
            itemView.item_sale_name.text = sale.barberName
            itemView.item_sale_creation_time.text = sale.creationTime
            itemView.item_sale_price.text = sale.salePrice.formatsCurrencyForBrazilian()
            itemView.item_sale_payment_method.text = sale.paymentMethod
        }
    }
}



