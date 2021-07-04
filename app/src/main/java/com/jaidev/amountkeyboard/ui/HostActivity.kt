package com.jaidev.amountkeyboard.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.jaidev.amountkeyboard.R
import com.jaidev.amountkeyboard.base.BaseActivity
import com.jaidev.amountkeyboard.base.BaseViewModel
import com.jaidev.amountkeyboard.databinding.ActivityHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HostActivity : BaseActivity<ActivityHostBinding>(R.layout.activity_host),
    NavController.OnDestinationChangedListener {

    override fun getVM(): BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.amountFragment -> {
                supportActionBar?.hide()
            }
            else -> {
                supportActionBar?.show()
            }
        }
    }
}