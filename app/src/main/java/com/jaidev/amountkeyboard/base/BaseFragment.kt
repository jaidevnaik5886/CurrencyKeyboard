package com.jaidev.amountkeyboard.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment



abstract class BaseFragment<out T : ViewDataBinding>(
    @LayoutRes val layout: Int
) : Fragment() {

    var toolbarTitle: Int? = null

    constructor(@LayoutRes layout: Int, @StringRes toolbarTitle: Int): this(layout) {
        this.toolbarTitle = toolbarTitle
    }

    val binding: T by lazy {
        DataBindingUtil.inflate(layoutInflater, layout, null, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolbarTitle?.apply { setToolbarTitle(this) }
        setHasOptionsMenu(true)
        attachBinding()
        return binding.root
    }

    open fun attachBinding() {
    }

    abstract fun getVM(): BaseViewModel

    open fun handleEvent(event: BaseEvent) {

    }

    fun setToolbarTitle(@StringRes title: Int) {
        if (activity is BaseActivity<*>) {
            (activity as BaseActivity<*>).supportActionBar?.setTitle(title)
        }
    }
}