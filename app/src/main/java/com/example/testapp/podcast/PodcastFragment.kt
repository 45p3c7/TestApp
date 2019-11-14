package com.example.testapp.podcast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.testapp.BaseFragment
import kotlinx.android.synthetic.main.content.*

class PodcastFragment : BaseFragment() {

    private val viewModel : PodcastViewModel by viewModels { viewModelFactory }

    override fun onStart() {
        super.onStart()
        viewModel.loadContent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.content.observe(viewLifecycleOwner, Observer(contentAdapter::setData))
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) loadingIndicator.show() else loadingIndicator.hide()
        }
    }

    override fun navigateToDetail(id: String) {
        findNavController().navigate(PodcastFragmentDirections.navigateToDetail(id))
    }

    override fun handleCheckbox(id: String, isFavorite: Boolean, adapterPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}