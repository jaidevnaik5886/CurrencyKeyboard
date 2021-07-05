package com.jaidev.amountkeyboard.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun test_currency_format() {
        //arrange
        val currentDate = "124864.23"
        //actual
        val actual: Pair<String, String> = Utils.getCurrencyWithPrefix(currentDate, "AED ")
        // assert
        assertEquals("AED 124,864.23", actual.first)
    }

    @Test
    fun check_for_number_format_exception() {
        //arrange
        val currentDate = "abcde"
        //actual
        val actual: Pair<String, String> = Utils.getCurrencyWithPrefix(currentDate, "AED ")
        // assert
        assertEquals("", actual.first)
    }

    @Test
    fun check_if_currency_unit_is_not_compulsory() {
        //arrange
        val currentDate = "1245.32"
        //actual
        val actual: Pair<String, String> = Utils.getCurrencyWithPrefix(currentDate)
        // assert
        assertEquals("1,245.32", actual.first)
    }

    @Test
    fun check_if_it_can_take_any_length() {
        //arrange
        val currentDate = "123456789.1210"
        //actual
        val actual: Pair<String, String> = Utils.getCurrencyWithPrefix(currentDate)
        // assert
        assertEquals("Case not Handled", "123,456,789.12", actual.first)
    }

    @Test
    fun check_if_it_takes_more_than_two_decimal() {
        //arrange
        val currentDate = "4.2356"
        //actual
        val actual: Pair<String, String> = Utils.getCurrencyWithPrefix(currentDate)
        // assert
        assertEquals("Case not Handled", "4.23", actual.first)
    }
}