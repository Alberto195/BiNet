package com.example.binettest.presentation.add_entry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.binettest.databinding.FragmentAddEntryBinding
import com.example.binettest.presentation.add_entry.actions.AddEntryAction
import com.example.binettest.presentation.add_entry.viewmodels.AddEntryViewModel
import com.example.binettest.presentation.add_entry.viewstates.AddEntryViewState
import com.example.binettest.presentation.base.ui.BaseFragment
import kotlin.reflect.KClass

class AddEntryFragment : BaseFragment<
        AddEntryViewState,
        AddEntryAction,
        AddEntryViewModel,
        FragmentAddEntryBinding
        >() {
    override fun getViewModelClass(): KClass<AddEntryViewModel> = AddEntryViewModel::class

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddEntryBinding =
        FragmentAddEntryBinding.inflate(
            inflater,
            container,
            false
        )

    override fun render(viewState: AddEntryViewState) {
        when (viewState) {
            is AddEntryViewState.AddFailed -> addingEntryFailed()
            is AddEntryViewState.AddSuccessful -> addingEntrySuccessful()
            is AddEntryViewState.CleanFailed -> cleaningBodyFailed()
            is AddEntryViewState.CleanSuccessful -> cleaningBodySuccessful()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.add.setOnClickListener {
            dispatchAction(
                AddEntryAction.SaveClicked(
                    binding.entryBody.text.toString()
                )
            )
        }
        binding.clean.setOnClickListener {
            dispatchAction(
                AddEntryAction.CleanClicked
            )
        }
    }

    private fun addingEntryFailed() {
        Toast.makeText(
            requireContext(),
            "Запись НЕ добавлена! Проверьте соединение!",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun addingEntrySuccessful() {
        Toast.makeText(requireContext(), "Запись добавлена!", Toast.LENGTH_LONG).show()
    }

    private fun cleaningBodyFailed() {
        Toast.makeText(requireContext(), "Запись не очистена!", Toast.LENGTH_LONG).show()
    }

    private fun cleaningBodySuccessful() {
        binding.entryBody.text.clear()
        Toast.makeText(requireContext(), "Запись очистена!", Toast.LENGTH_LONG).show()
    }
}