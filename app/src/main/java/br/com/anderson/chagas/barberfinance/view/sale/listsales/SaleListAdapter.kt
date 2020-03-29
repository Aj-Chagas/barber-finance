package br.com.anderson.chagas.barberfinance.view.sale.listsales

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.app.extension.formatsCurrencyForBrazilian
import br.com.anderson.chagas.barberfinance.model.Sale
import kotlinx.android.synthetic.main.item_sale.view.*

class SaleListAdapter(private val sales: MutableList<Sale> = mutableListOf(),
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

    fun update(sales: List<Sale>) {
        notifyItemRangeRemoved(0, this.sales.size)
        this.sales.clear()
        this.sales.addAll(sales)
        notifyItemRangeInserted(0, this.sales.size)
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



