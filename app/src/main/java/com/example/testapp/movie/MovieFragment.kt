package com.example.testapp.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.testapp.BaseFragment
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.content.*

class MovieFragment : BaseFragment(), HasAndroidInjector {

    private val viewModel : MovieViewModel by viewModels { viewModelFactory }

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
        findNavController().navigate(MovieFragmentDirections.navigateToDetail(id))
    }

    override fun handleCheckbox(id: String, isFavorite: Boolean, adapterPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}