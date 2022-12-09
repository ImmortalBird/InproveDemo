package com.boolaw.myapplication

class T799ChampagneTower {
    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        val count = getCount(query_row + 1)

        if (poured > count) {
            return 1.0
        } else {
            var row = doubleArrayOf(poured.toDouble())
            for (i in 1..query_row) {
                val nextRow = DoubleArray(i + 1)
                for (j in 0 until i) {
                    val volume = row[j]
                    if (volume > 1) {
                        nextRow[j] += (volume - 1) / 2
                        nextRow[j + 1] += (volume - 1) / 2
                    }
                }
                row = nextRow
            }
            return Math.min(1.0, row[query_glass])
        }
    }

    /**
     *
     */
    fun getCount(row: Int) = Math.pow(2.0, row.toDouble()) - 1
//    fun getCount(row: Int) = 2.0.pow(row.toDouble()) -1


//    fun f2(row: Int, index: Int): Double {
//        if (index == 0 || index == row)
//            return 1.0 / Math.pow(2.0, row.toDouble())
//        if (index == 1 || index == row - 1)
//            return (row + 1.0) / Math.pow(2.0, row.toDouble())
//        return f2(row - 1, index) + f2(row - 1, index - 1)
//    }


}

fun main() {
//    T799ChampagneTower().champagneTower(2,1,1)
//    T799ChampagneTower().champagneTower(25, 6, 1)

    println(T799ChampagneTower().champagneTower(6, 3, 2))
}