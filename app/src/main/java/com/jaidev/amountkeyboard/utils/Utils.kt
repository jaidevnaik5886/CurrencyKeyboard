package com.jaidev.amountkeyboard.utils

import java.text.NumberFormat
import java.util.*

class Utils {
    companion object {

        fun getCurrencyWithPrefix(value: String?, prefix: String = ""): Pair<String, String> {
            try {
                if (!value.isNullOrEmpty()) {
                    val formattedValue = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
                        .format(value.toDouble()).removePrefix("¤")
                    if (value.isNotEmpty() && formattedValue.endsWith(".00")) {
                        when {
                            value.endsWith(".") -> { //if it is X.
                                return Pair("$prefix${formattedValue.removeSuffix("00")}", "00")
                            }
                            value.endsWith(".0") -> { //if it is X.0
                                return Pair("$prefix${formattedValue.removeSuffix("0")}", "0")
                            }
                            value.endsWith(".00") -> { //if it is X.00
                                return Pair("$prefix$formattedValue", "")
                            }
                            //when entered number never have .00
                            else -> return Pair(
                                "$prefix${formattedValue.removeSuffix(".00")}",
                                ".00"
                            )
                        }
                    } else if (formattedValue.contains(".") &&
                        (formattedValue.substring(formattedValue.indexOf(Constants.DOT))
                            .substring(1).contains(value.last())
                                || formattedValue.last() == value.last())
                    ) {
                        //if the number contains . and followed by all other values
                        val toRemove =
                            formattedValue.substring(formattedValue.lastIndexOf(value.last()))
                                .substring(1)
                        return Pair("$prefix${formattedValue.removeSuffix(toRemove)}", toRemove)
                    }
                    //no change required
                    return Pair("$prefix$formattedValue", "")
                }
                return Pair("", "")
            } catch (e: Exception) {
                return Pair("", "")
            }
        }

    }
}