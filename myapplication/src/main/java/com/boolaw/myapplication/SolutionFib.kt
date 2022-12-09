package com.boolaw.myapplication

class SolutionFib {
    val mod = 1000000007
    fun fib(n : Int):Int{
        if (n < 2) return n
        return power(arrayOf(intArrayOf(1,1),intArrayOf(1,0)), n -1)
    }


    fun power(a:Array<IntArray>, m: Int) : Int{
        var n = m
        var arr = a
        val r = arrayOf(1,0)
        var r0 =r[0]
        while (n>0){
            if (n and 1 == 1 ){
                r[0] = ((arr[0][0].toLong()* r0  + arr[0][1].toLong() * r[1]) % mod).toInt()
                r[1] = ((arr[1][0].toLong()* r0  + arr[1][1].toLong() * r[1]) % mod).toInt()
                r0 =  r[0]
            }
            n = n shr 1
            arr = multiplyMatrix(arr,arr)
        }
        return r[0]

    }


    fun multiplyMatrix(a:Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
        val  c = arrayOf(IntArray(2),IntArray(2))
        (0..1).forEach {i ->
            (0..1).forEach {j ->
                c[i][j] = ((a[i][0].toLong()* b[0][j]  + a[i][1].toLong() * b[1][j]) % mod).toInt()
            }
        }
        return c
    }

}

fun main() {
    println(SolutionFib().fib(100))
}