package br.com.anderson.chagas.barberfinance.extension

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showError(msg: String){
    Snackbar.make(
        activity!!.findViewById(android.R.id.content),
        msg,
        Snackbar.LENGTH_LONG
    ).show()
}