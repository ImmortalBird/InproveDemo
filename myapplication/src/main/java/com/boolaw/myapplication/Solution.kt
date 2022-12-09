package com.boolaw.myapplication

class Solution {
    fun fib(n: Int): Int {
        if (n < 2) {
            return n
        }
        val q = arrayOf(intArrayOf(1, 1), intArrayOf(1, 0))
        val res = pow(q, n - 1)
        return res[0][0]
    }

    fun pow(a: Array<IntArray>, n: Int): Array<IntArray> {
        var a = a
        var n = n
        var ret = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))
        while (n > 0) {
            if (n and 1 == 1) {
                ret = multiply(ret, a)
            }
            n = n shr 1
            a = multiply(a, a)
//            println(a.contentDeepToString())
//            println("-----------pow-------------")
        }
        return ret
    }

    fun multiply(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
        val c = Array(2) {
            IntArray(
                2
            )
        }
        for (i in 0..1) {
            for (j in 0..1) {
                c[i][j] = ((a[i][0].toLong() * b[0][j] + a[i][1].toLong() * b[1][j]) % MOD).toInt()
            }
        }
//        println(a.contentDeepToString())
//        println(b.contentDeepToString())
//        println("------------------------")
        return c
    }

    companion object {
        const val MOD = 1000000007
    }
}

fun fastPower(base: Int, power: Int): Int {
    var mBase = base
    var mPower = power
    var result = 1
    while (mPower > 0) {
        if ((mPower and 1) == 1) {
            result = mBase * result % 1000
            mPower = mPower.shr(1)
            mBase = mBase * mBase % 1000
        } else {
            mPower = mPower.shr(1)
            mBase = mBase * mBase % 1000
        }
    }
    return result
}

val mod = 1000000007
fun fib(n: Int): Int {
    if (n < 2) {
        return n
    }
    return power(arrayOf(intArrayOf(1, 1), intArrayOf(1, 0)), n - 1)
}

fun power(a: Array<IntArray>, power: Int): Int {
    var n = power
    var arr = a
    val sb = intArrayOf(1, 0)
    var sb0 = 0
    while (n > 0) {
        if ((n and 1) == 1) {
            sb0 = sb[0]
            sb[0] = ((arr[0][0].toLong() * sb[0] + arr[0][1].toLong() * sb[1]) % mod).toInt()
            sb[1] = ((arr[1][0].toLong() * sb0 + arr[1][1].toLong() * sb[1]) % mod).toInt()
        }
        n = n shr 1
        if (n > 0)
            arr = powerMatrix(arr, arr)
    }

    return sb[0]
}

fun powerMatrix(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
    val arr = arrayOf(IntArray(2), IntArray(2))
    for (x in 0..1) {
        for (y in 0..1) {
            arr[x][y] = ((a[x][0].toLong() * b[0][y] + a[x][1].toLong() * b[1][y]) % mod).toInt()
        }
    }
    return arr
}

fun main() {
//    Solution().fib(10)
    (2..100).forEach {

        println("--------------- $it")
        assert(Solution().fib(it) == SolutionFib().fib(it))
        println("循环：${Solution().fib(it)}")
        println("矩阵降幂：${fib(it)}")
        println("矩阵降幂2：${SolutionFib().fib(it)}")
    }
}