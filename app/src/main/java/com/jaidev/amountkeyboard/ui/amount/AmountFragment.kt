package com.jaidev.amountkeyboard.ui.amount

import androidx.fragment.app.viewModels
import com.jaidev.amountkeyboard.R
import com.jaidev.amountkeyboard.base.BaseFragment
import com.jaidev.amountkeyboard.base.BaseViewModel
import com.jaidev.amountkeyboard.databinding.FragmentAmountBinding
import com.jaidev.amountkeyboard.utils.Utils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AmountFragment : BaseFragment<FragmentAmountBinding>(R.layout.fragment_amount) {

    private val model: AmountViewModel by viewModels()

    override fun attachBinding() {
        binding.handler = this
        binding.vm = model
        model.amount.observe(viewLifecycleOwner, {
            val amount = Utils.getCurrencyWithPrefix(it, getString(R.string.aed_label))
            binding.edtAmount.setText(amount.first)
            binding.txtAmount.text = amount.second
        })
    }

    override fun getVM(): BaseViewModel = model

}


