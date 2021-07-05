package com.jaidev.amountkeyboard.ui.amount

import androidx.lifecycle.MutableLiveData
import com.jaidev.amountkeyboard.base.BaseViewModel
import com.jaidev.amountkeyboard.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AmountViewModel @Inject constructor(
) : BaseViewModel() {

    val amount: MutableLiveData<String> = MutableLiveData<String>("")

    fun onNumberClicked(numberPad: NumberPad) {
        var totalLength = 10
        val maxDecimalLength = 2
        val maxNumberLength = 7
        if (amount.value!!.startsWith(Constants.ZERO) && !amount.value!!.contains(Constants.DOT)) {
            amount.value = amount.value!!.removePrefix(Constants.ZERO)
        }
        if (amount.value!!.contains(Constants.DOT)) {
            val afterDecimal =
                amount.value!!.substring(amount.value!!.indexOf(Constants.DOT)).substring(1)
            if (afterDecimal.length > (maxDecimalLength - 1)) {
                return
            }
        } else {
            totalLength = maxNumberLength
        }
        if (amount.value!!.length < totalLength) {
            generateNumber(numberPad)
        }
    }

    fun onDotClicked() {
        if (amount.value!!.isNotEmpty() &&
            !amount.value!!.contains(Constants.DOT)
        ) {
            amount.value = amount.value.plus(Constants.DOT)
            return
        }
    }

    private fun generateNumber(numberPad: NumberPad) {
        amount.value = when (numberPad) {
            NumberPad.ONE -> amount.value.plus(Constants.ONE)
            NumberPad.TWO -> amount.value.plus(Constants.TWO)
            NumberPad.THREE -> amount.value.plus(Constants.THREE)
            NumberPad.FOUR -> amount.value.plus(Constants.FOUR)
            NumberPad.FIVE -> amount.value.plus(Constants.FIVE)
            NumberPad.SIX -> amount.value.plus(Constants.SIX)
            NumberPad.SEVEN -> amount.value.plus(Constants.SEVEN)
            NumberPad.EIGHT -> amount.value.plus(Constants.EIGHT)
            NumberPad.NINE -> amount.value.plus(Constants.NINE)
            NumberPad.ZERO -> amount.value.plus(Constants.ZERO)
            else -> ""
        }
    }

    fun onBackSpaceClicked() {
        if (amount.value!!.isEmpty()) {
            amount.value = ""
        } else {
            val newAmount = StringBuilder(amount.value!!)
                .deleteCharAt(amount.value!!.length - 1).toString()
            amount.value = newAmount
        }
    }


}

enum class NumberPad { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ZERO, DOT }

