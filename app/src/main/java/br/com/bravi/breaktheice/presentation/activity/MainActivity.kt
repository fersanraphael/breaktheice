package br.com.bravi.breaktheice.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import br.com.bravi.breaktheice.R
import br.com.bravi.breaktheice.databinding.ActivityMainBinding
import br.com.bravi.breaktheice.presentation.state.MainUiState
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Raphael Santos
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this@MainActivity
        binding.viewModel = viewModel

        setSupportActionBar(binding.appBar)

        fetchUiState()

        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment? ?: return
        val navController: NavController = navHostFragment.navController
        setupActionBarWithNavController(navController)
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
