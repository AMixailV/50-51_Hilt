package ru.mixail_akulov.a50_51_hilt.app.screens.main.tabs.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.mixail_akulov.a50_51_hilt.app.R
import ru.mixail_akulov.a50_51_hilt.app.databinding.FragmentBoxBinding
import ru.mixail_akulov.a50_51_hilt.app.screens.base.BaseFragment
import ru.mixail_akulov.a50_51_hilt.app.utils.observeEvent
import ru.mixail_akulov.a50_51_hilt.app.utils.viewModelCreator
import ru.mixail_akulov.a50_51_hilt.app.views.DashboardItemView
import javax.inject.Inject

@AndroidEntryPoint
class BoxFragment : BaseFragment(R.layout.fragment_box) {

    @Inject lateinit var factory: BoxViewModel.Factory
    override val viewModel by viewModelCreator {
        factory.create(args.boxId)
    }

    private lateinit var binding: FragmentBoxBinding

    private val args by navArgs<BoxFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        binding.root.setBackgroundColor(DashboardItemView.getBackgroundColor(getColorValue()))
        binding.boxTextView.text = getString(R.string.this_is_box, getColorName())

        binding.goBackButton.setOnClickListener { onGoBackButtonPressed() }

        listenShouldExitEvent()
    }

    private fun onGoBackButtonPressed() {
        findNavController().popBackStack()
    }

    private fun listenShouldExitEvent() = viewModel.shouldExitEvent.observeEvent(viewLifecycleOwner) { shouldExit ->
        if (shouldExit) {
            // close the screen if the box has been deactivated
            findNavController().popBackStack()
        }
    }

    private fun getColorValue(): Int = args.colorValue

    private fun getColorName(): String = args.colorName
}