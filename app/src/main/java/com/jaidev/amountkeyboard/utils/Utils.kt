package com.jaidev.amountkeyboard.utils

import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {

        fun geFormattedCurrency(value: String?): String {
            if (!value.isNullOrEmpty()) {
                val formattedValue = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
                    .format(value.toDouble()).removePrefix("¤")
                if (value.isNotEmpty() && formattedValue.endsWith(".00")) {
                    if (value.endsWith(".")) {
                        return "AED ${formattedValue.removeSuffix("00")}"
                    } else if (value.endsWith(".0")) {
                        return "AED ${formattedValue.removeSuffix("0")}"
                    } else if (value.endsWith("0.00")) {
                        return "AED $formattedValue"
                    }
                    return "AED ${formattedValue.removeSuffix(".00")}"
                } else if (formattedValue.contains(".")) {
                    val toRemove =
                        formattedValue.substring(formattedValue.lastIndexOf(value.last()))
                            .substring(1)
                    return "AED ${formattedValue.removeSuffix(toRemove)}"
                }
                return "AED $formattedValue"
            }
            return ""
        }

        fun getCurrency(value: String?): Pair<String,String> {
            if (!value.isNullOrEmpty()) {
                val formattedValue = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
                    .format(value.toDouble()).removePrefix("¤")
                if (value.isNotEmpty() && formattedValue.endsWith(".00")) {
                    if (value.endsWith(".")) {
                        return Pair("AED ${formattedValue.removeSuffix("00")}","00")
                    } else if (value.endsWith(".0")) {
                        return  Pair("AED ${formattedValue.removeSuffix("0")}","0")
                    } else if (value.endsWith("0.00")) {
                        return Pair("AED $formattedValue","")
                    }
                    return Pair("AED ${formattedValue.removeSuffix(".00")}",".00")
                } else if (formattedValue.contains(".")) {
                    val toRemove =
                        formattedValue.substring(formattedValue.lastIndexOf(value.last()))
                            .substring(1)
                    return Pair("AED ${formattedValue.removeSuffix(toRemove)}",toRemove)
                }
                return Pair("AED $formattedValue","")
            }
            return Pair("","")
        }

    }
}