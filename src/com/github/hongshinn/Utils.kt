package com.github.hongshinn

import java.util.*
import kotlin.math.pow

/**
 * @author hsn
 * @version 1.0
 * @since 22-07-12 06:08
 */
object Utils {
    fun getBanDate(days: Int): Date? {
        val d: Date?
        val ca: Calendar? = Calendar.getInstance()
        ca!!.add(Calendar.DATE, days)
        d = ca.getTime()
        return d
    }

    fun variance(array: MutableList<Double?>?): Double {
        var sum: Double = 0.0
        for (d: Double? in array!!) {
            sum += d!!
        }
        val mean: Double = sum / array.size
        var variance: Double = 0.0
        for (d: Double? in array) {
            variance += (d!! - mean).pow(2.0)
        }
        return variance / array.size
    }

    fun varianceInt(array: MutableList<Int?>?): Double {
        var sum: Int = 0
        for (i: Int? in array!!) {
            sum += i!!
        }
        val mean: Double = sum.toDouble() / array.size
        var variance: Double = 0.0
        for (i: Int? in array) {
            variance += (i!! - mean).pow(2.0)
        }
        return variance / array.size
    }
}