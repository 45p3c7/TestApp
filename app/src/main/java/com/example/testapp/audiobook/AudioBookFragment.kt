package com.example.testapp.audiobook

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.testapp.BaseFragment
import com.example.testapp.EventObserver
import kotlinx.android.synthetic.main.content.*

class AudioBookFragment : BaseFragment() {

    private val viewModel: AudioBookViewModel by viewModels { viewModelFactory }

    override fun onStart() {
        super.onStart()
        viewModel.loadContent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setErrorObserver()
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) loadingIndicator.show() else loadingIndicator.hide()
        }
        viewModel.content.observe(viewLifecycleOwner, Observer(contentAdapter::setData))
    }


    private fun setErrorObserver() {
        viewModel.errorMessage.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun navigateToDetail(id: String) {
        findNavController().navigate(AudioBookFragmentDirections.navigateToDetail(id))
    }

    override fun handleCheckbox(id: String, isFavorite: Boolean, adapterPosition: Int) {
        viewModel.handleFavorite(id, isFavorite, adapterPosition)
    }
}