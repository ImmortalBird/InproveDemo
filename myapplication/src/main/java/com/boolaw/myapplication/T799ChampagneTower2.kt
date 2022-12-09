package com.boolaw.myapplication

/**
 * 香槟塔
 * https://leetcode.cn/problems/champagne-tower/
 */
class T799ChampagneTower2 {
    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        // 边缘的杯子如果满了，那么整行的杯子就满了,香槟数量超过
        val count = Math.pow(2.0, ((query_row + 1)).toDouble()) - 1

        if (poured > count) return 1.0
        var ret = doubleArrayOf(poured.toDouble())
        var s: Double
        (1..query_row).forEach { row ->
            val nextRet = DoubleArray(row + 1)
            (0 until row).forEach { index ->
                s = ret[index]
                if (s > 1) {
                    nextRet[index] += (s - 1) / 2
                    nextRet[index + 1] += (s - 1) / 2
                }
            }
            ret = nextRet
        }
        return Math.min(1.0, ret[query_glass])
    }
}

fun main() {

    println(T799ChampagneTower2().champagneTower(6, 3, 2))
}