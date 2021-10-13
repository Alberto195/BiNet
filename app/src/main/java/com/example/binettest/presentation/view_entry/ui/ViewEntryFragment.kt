package com.example.binettest.presentation.view_entry.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.binettest.databinding.FragmentViewEntryBinding
import com.example.binettest.presentation.base.ui.BaseFragment
import com.example.binettest.presentation.base.viewstate.BaseViewState
import com.example.binettest.presentation.entry_list.viewstate.EntryListViewState
import com.example.binettest.presentation.view_entry.actions.ViewEntryAction
import com.example.binettest.presentation.view_entry.viewmodels.ViewEntryViewModel
import com.example.binettest.presentation.view_entry.viewstate.ViewEntryViewState
import kotlin.reflect.KClass

class ViewEntryFragment : BaseFragment<
        ViewEntryViewState,
        ViewEntryAction,
        ViewEntryViewModel,
        FragmentViewEntryBinding
        >() {

    override fun getViewModelClass(): KClass<ViewEntryViewModel> = ViewEntryViewModel::class

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentViewEntryBinding =
        FragmentViewEntryBinding.inflate(
            inflater,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWholeEntry()
    }

    private fun showEntry() {
        viewModel.getEntry().let {
            binding.da.text = it?.dateAdded
            binding.dm.text = it?.dateModified
            binding.bodyContent.text = it?.bodyText
        }
    }

    private fun loadFailed() {
        Toast.makeText(
            requireContext(),
            "Ошибка! Проверьте соединение",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun render(viewState: ViewEntryViewState) {
        when (viewState) {
            is ViewEntryViewState.EntryFailed -> loadFailed()
            is ViewEntryViewState.EntryLoaded -> showEntry()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteEntry()
    }
}