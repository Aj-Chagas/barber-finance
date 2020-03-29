package br.com.anderson.chagas.barberfinance.extension

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.anderson.chagas.barberfinance.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showError(msg: String){
    Snackbar.make(
        activity!!.findViewById(android.R.id.content),
        msg,
        Snackbar.LENGTH_LONG
    ).show()
}

fun Fragment.setActionbarTitle(@StringRes titleRes: Int, enableBackNavigation: Boolean = false) {
    (activity as AppCompatActivity).supportActionBar?.run {
        setTitle(titleRes)
        setDisplayHomeAsUpEnabled(enableBackNavigation)
        setHomeAsUpIndicator(R.drawable.ic_arrow_left_red)

    }
}