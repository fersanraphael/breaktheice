package br.com.bravi.breaktheice.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.bravi.breaktheice.databinding.FragmentActivityDetailBinding
import br.com.bravi.breaktheice.presentation.fragment.base.BaseFragment
import br.com.bravi.breaktheice.presentation.popBack
import br.com.bravi.breaktheice.presentation.state.MainUiState
import kotlinx.coroutines.launch

/**
 * @author Raphael Santos
 */
class ActivityDetailFragment : BaseFragment() {

    private val arguments by navArgs<ActivityDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View {
        val binding: FragmentActivityDetailBinding = FragmentActivityDetailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = fragmentActivity
        binding.viewModel = viewModel

        fragmentActivity.onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    popBack()
                }
            }
        )

        fetchResult()

        viewModel.getActivity(arguments.activityId)

        return binding.root
    }

    private fun fetchResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                if (uiState is MainUiState.GetActivity) {
                    Log.d(TAG, uiState.activityModel.toString())
                }
            }
        }
    }

    companion object {

        private const val TAG: String = "ActivityDetailFragment"
    }
}
