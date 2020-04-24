package br.com.anderson.chagas.barberfinance.app.extension

import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import br.com.anderson.chagas.barberfinance.R
import com.google.android.material.snackbar.Snackbar


fun Fragment.showMsg(
    msg: String,
    placeSnackBar: View
){
    if(this != null ){
        Snackbar.make(placeSnackBar, msg, Snackbar.LENGTH_LONG)
            .setAction("Fechar"){
                println("Fechar")
            }.show();
    }

}

fun Fragment.setActionbarTitle(@StringRes titleRes: Int, enableBackNavigation: Boolean = false) {
    (activity as AppCompatActivity).supportActionBar?.run {
        setTitle(titleRes)
        setDisplayHomeAsUpEnabled(enableBackNavigation)
        setHomeAsUpIndicator(R.drawable.ic_arrow_left_red)
    }
}