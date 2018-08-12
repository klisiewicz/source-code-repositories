package pl.karollisiewicz.reposistory.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import pl.karollisiewicz.reposistory.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.navHostFragment)
        setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.navHostFragment).navigateUp()
}
