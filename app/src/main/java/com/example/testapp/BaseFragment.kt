package com.example.testapp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.content.*
import javax.inject.Inject

abstract class BaseFragment: Fragment(R.layout.content), HasAndroidInjector {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    protected var contentAdapter = ContentAdapter(object : ContentItemListener {
        override fun onItemClick(id: String) {
            navigateToDetail(id)
        }

        override fun onCheck(id: String, isFavorite: Boolean, adapterPosition: Int) {
            handleCheckbox(id, isFavorite, adapterPosition)
        }
    })

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var  viewModelFactory : ViewModelProvider.Factory

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val layoutManager = LinearLayoutManager(context)
        content_recycler.layoutManager = layoutManager
        content_recycler.adapter = contentAdapter
        content_recycler.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    abstract fun navigateToDetail(id : String)
    abstract fun handleCheckbox(id: String, isFavorite: Boolean, adapterPosition: Int)
}