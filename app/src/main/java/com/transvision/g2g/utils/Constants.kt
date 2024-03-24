package com.transvision.g2g.utils

import java.util.Calendar

object Constants {
    const val DB_Name = "MD_KPTCLG2G"
    const val APIPassword = "Tvd1234!"
    const val MISG2G = "MISG2G"

    fun generateFinancialYears(startYear: Int, numberOfYears: Int): List<String> {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val financialYears = mutableListOf<String>()
        for (i in 0 until numberOfYears) {
            val year = startYear + i
            financialYears.add("$year-${(year + 1) % 100}")
        }
        return financialYears.reversed() // Reverse if needed, depends on the order you want
    }

    fun getCurrentFinancialYear() : String{
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        return "$currentYear-${(currentYear + 1) % 100}"
    }

    val getCurrentYear
        get() = Calendar.getInstance().get(Calendar.YEAR)
}