package com.example.testapp.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.example.testapp.BaseFragment
import kotlinx.android.synthetic.main.content.*

class FavoriteFragment : BaseFragment() {

    private val viewModel: FavoriteViewModel by viewModels { viewModelFactory }

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

    }

    override fun handleCheckbox(id: String, isFavorite: Boolean, adapterPosition: Int) {
        viewModel.deleteFavorite(id, isFavorite, adapterPosition)
    }
}