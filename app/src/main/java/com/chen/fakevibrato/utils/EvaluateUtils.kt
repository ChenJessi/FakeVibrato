package com.chen.fakevibrato.utils

/**
 * 估值器
 */
object EvaluateUtils {

    /**
     * 估值器
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    fun evaluate(fraction: Float, startValue: Number, endValue: Number): Float {
        val startFloat = startValue.toFloat()
        return startFloat + fraction * (endValue.toFloat() - startFloat)
    }
}