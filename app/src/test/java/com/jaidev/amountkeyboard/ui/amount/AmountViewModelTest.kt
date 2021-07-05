package com.jaidev.amountkeyboard.ui.amount

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AmountViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AmountViewModel

    @Before
    fun setup() {
        viewModel = AmountViewModel()
    }

    @Test
    fun check_if_it_types_number_from_0_to_9() {
        viewModel.onNumberClicked(NumberPad.SEVEN)
        val amount = viewModel.amount
        assertEquals("7", amount.value)
    }

    @Test
    fun check_if_backspace_clears_one_char_at_a_time() {
        viewModel.amount.value = "1565.02"
        viewModel.onBackSpaceClicked()
        val amount = viewModel.amount
        assertEquals("1565.0", amount.value)
    }

    @Test
    fun check_if_it_takes_dot_before_any_number() {
        viewModel.onDotClicked()
        val amount = viewModel.amount
        assertEquals("", amount.value)
    }

    @Test
    fun check_if_it_takes_dot_when_it_already_has_dot() {
        viewModel.amount.value = "1."
        viewModel.onDotClicked()
        val amount = viewModel.amount
        assertEquals("1.", amount.value)
    }
}