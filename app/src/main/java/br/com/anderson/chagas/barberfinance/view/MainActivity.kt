package br.com.anderson.chagas.barberfinance.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import br.com.anderson.chagas.barberfinance.R
import br.com.anderson.chagas.barberfinance.view.result.InputDatePickerDialog
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statusBarWhiteWithBackIcons()
        setupNavigation()

    }

    private fun setupNavigation() {
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        navController = findNavController(R.id.nav_host_fragment_container)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_sales,
                R.id.navigation_result,
                R.id.navigation_method_payment,
                R.id.navigation_service_cost,
                R.id.navigation_concluded
            )
        )

        //setupActionBarWithNavController(navController, appBarConfiguration)
        NavigationUI.setupActionBarWithNavController(this, navController)
        bottomNavView.setupWithNavController(navController)
    }


    /**
    * Support for API below 23, already API above 23 use in style <item name="android:windowLightStatusBar">true</item>
    * */
    private fun statusBarWhiteWithBackIcons() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
