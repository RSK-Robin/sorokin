package com.sorokin.gifviewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sorokin.gifviewer.databinding.FragmentPageBinding
import com.sorokin.gifviewer.utils.*

class SectionGifFragment : Fragment() {

    private val binding by binding { FragmentPageBinding.bind(requireView()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments: GifsType = arguments?.getParcelable(SECTION_TYPE) ?: GifsType.RANDOM
        val viewModel by viewModels<GifViewModel> {
            viewModelFactory { application ->
                GifViewModel(application, arguments)
            }
        }

        binding.buttonNext.apply {
            setOnClickListener { viewModel.nextButtonClicked() }
        }

        binding.buttonPreviously.apply {
            setOnClickListener { viewModel.previouslyButtonClicked() }
        }

        binding.repeat.apply {
            setOnClickListener { viewModel.repeatButtonClicked() }
        }

        viewModel.state.collectOnStarted(viewLifecycleOwner) { state ->
            when (state) {
                is GifState.Error -> {
                    showError()
                    binding.apply {
                        buttonNext.visible(state.visibilityOfButtonNext)
                        buttonPreviously.visible(state.visibilityOfButtonPreviously)
                        progressBar.visible(false)
                    }
                }
                is GifState.Loading -> {
                    hideError()
                    binding.apply {
                        buttonNext.visible(state.visibilityOfButtonNext)
                        buttonPreviously.visible(state.visibilityOfButtonPreviously)
                        progressBar.visible(true)
                    }
                }
                is GifState.Started -> {
                    binding.buttonPreviously.visible(state.visibilityOfButtonPreviously)
                }
                is GifState.Success -> {
                    hideError()
                    binding.apply {
                        gif.loadGif(state.gif.gifUrl)
                        title.text = state.gif.description
                        buttonNext.visible(state.visibilityOfButtonNext)
                        buttonPreviously.visible(state.visibilityOfButtonPreviously)
                        progressBar.visible(false)
                    }
                }
            }
        }
    }

    private fun showError() {
        binding.viewSwitcher.displayedChild = 1
    }

    private fun hideError() {
        binding.viewSwitcher.displayedChild = 0
    }

    companion object {
        const val SECTION_TYPE = "SECTION_TYPE"

        fun newInstance(sectionType: GifsType) = SectionGifFragment().apply {
            arguments = bundleOf(SECTION_TYPE to sectionType)
        }
    }
}
