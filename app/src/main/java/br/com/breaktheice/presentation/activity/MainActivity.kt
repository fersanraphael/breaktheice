package br.com.breaktheice.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.breaktheice.R
import br.com.breaktheice.databinding.ActivityMainBinding
import br.com.breaktheice.presentation.fragment.ActivityListFragmentDirections
import br.com.breaktheice.presentation.state.MainUiState
import br.com.breaktheice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Raphael Santos
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar)

        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = viewModel
        binding.appBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.settings -> {
                    // TODO: Temporary.
                    navHostFragment.navController.navigate(ActivityListFragmentDirections.actionListToFilter())
                    true
                }
                else -> {
                    false
                }
            }
        }

        fetchUiState()

        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment? ?: return

        setupActionBarWithNavController(navHostFragment.navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun fetchUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (uiState is MainUiState.Error) {
                    getString(R.string.label_common_error).showAsToast()
                }
            }
        }
    }

    private fun String.showAsToast() {
        Toast.makeText(applicationContext, this, Toast.LENGTH_SHORT).show()
    }
}
