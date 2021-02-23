package com.example.test2

/**
 * 一般选择题 用一个 Int 记录 题型 和 答案
 * 32位的int值：
 *      左侧5位记录题型，可以记录32种题型
 *      右侧4位记录答案，ABCD四种选项的答案
 *          从右到左，依次为 A B C D...
 *
 * 特殊选择：
 *  七选五
 *      每三位表示一个字母
 *      从右到左 依次为 A B C D E F G
 *      001 A
 *      010 B
 *      011 C
 *      100 D
 *      101 E
 *      110 F
 *      111 G
 */
class KeyTransform {


    /**
     * 将 二进制转换成字符串
     *
     * @param byte Int
     * @return String
     */
    fun byte2Key(byte: Int): String {
        if (byte >= 0xF || byte < 1)
            throw Throwable("参数不合法，必须是1-15")
        return when (byte) {
            1 -> "D"        // 0001
            2 -> "C"        // 0010
            3 -> "CD"       // 0011
            4 -> "B"        // 0100
            5 -> "BD"       // 0101
            6 -> "BC"       // 0110
            7 -> "BCD"      // 0111
            8 -> "A"        // 1000
            9 -> "AD"       // 1001
            10 -> "AC"      // 1010
            11 -> "ACD"     // 1011
            12 -> "AB"      // 1100
            13 -> "ABD"     // 1101
            14 -> "ABC"     // 1110
            15 -> "ABCD"    // 1111
            else -> "ABCD"
        }
    }

    /**
     * 将 二进制转换成字符串
     *
     * @param byte Int
     * @return String
     */
    fun byte2Key2(byte: Int): String {
        if (byte >= 0xF || byte < 1)
            throw Throwable("参数不合法，必须是1-15")
        return when (byte) {
            1 -> "A"       // 0001
            2 -> "B"       // 0010
            3 -> "AB"      // 0011
            4 -> "C"       // 0100
            5 -> "AC"      // 0101
            6 -> "BC"      // 0110
            7 -> "ABC"     // 0111
            8 -> "D"       // 1000
            9 -> "AD"      // 1001
            10 -> "BD"      // 1010
            11 -> "ABD"     // 1011
            12 -> "CD"      // 1100
            13 -> "ACD"     // 1101
            14 -> "BCD"     // 1110
            15 -> "ABCD"    // 1111
            else -> "ABCD"
        }
    }

    /**
     * 将 二进制转换成字符串
     *
     * @param byte Int
     * @return String
     */
    fun byte2Key3(byte: Int): String {
        if (byte > 0xF || byte < 1)
            throw Throwable("参数不合法，必须是1-15")
        val sb = StringBuilder()
        for (i in 0 until 4) {
            if (byte.shr(i).and(1) == 1) {
                sb.append((i+65).toChar())
            }
        }
        return sb.toString()
    }

    /**
     * 将 字符串转为二进制
     * @param key String
     * @return Int
     */
    fun key2byte(key: String): Int {
//        Pattern.compile("^[A-D]{4}$")
        // TODO: 2021/2/22 正则校验 ，不规则抛异常
        return when (key) {
            "D" -> 1   // 0001
            "C" -> 2   // 0010
            "CD" -> 3   // 0011
            "B" -> 4   // 0100
            "BD" -> 5   // 0101
            "BC" -> 6   // 0110
            "BCD" -> 7   // 0111
            "A" -> 8   // 1000
            "AD" -> 9   // 1001
            "AC" -> 10  // 1010
            "ACD" -> 11  // 1011
            "AB" -> 12  // 1100
            "ABD" -> 13  // 1101
            "ABC" -> 14  // 1110
            "ABCD" -> 15  // 1111
            else -> 15
        }
    }

    /**
     * 将 字符串转为二进制
     * @param key String
     * @return Int
     */
    fun key2byte2(key: String): Int {
//        Pattern.compile("^[A-D]{4}$")
        // TODO: 2021/2/22 正则校验 ，不规则抛异常
        return when (key) {
            "A" -> 1   // 0001
            "B" -> 2   // 0010
            "AB" -> 3   // 0011
            "C" -> 4   // 0100
            "AC" -> 5   // 0101
            "BC" -> 6   // 0110
            "ABC" -> 7   // 0111
            "D" -> 8   // 1000
            "AD" -> 9   // 1001
            "BD" -> 10  // 1010
            "ABD" -> 11  // 1011
            "CD" -> 12  // 1100
            "ACD" -> 13  // 1101
            "BCD" -> 14  // 1110
            "ABCD" -> 15  // 1111
            else -> 15
        }
    }


    /**
     * 算得分
     * @param rightKey Int
     * @param userKey Int
     */
    fun checkResult(rightKey: Int, userKey: Int) {
        if (rightKey == userKey) {
            // TODO: 2021/2/22 正确
            println("正确")
        } else if (rightKey < userKey) {
            // TODO: 2021/2/22 全部错误
            println("全部错误")
        } else {
            if ((rightKey.and(userKey)) == userKey) {
                // TODO: 2021/2/22 半对，有漏选项
                println("半对，有漏选项")
            } else {
                // TODO: 2021/2/22 全部错误
                println("全部错误")
            }
        }
    }

    /**
     * 比对选项是否正确
     * @param rightKey Int
     * @param userKey Int
     * @param index Int
     */
    fun checkResult(rightKey: Int, userKey: Int, index: Int) {
        val options = arrayListOf<String>()

        for (i in options.indices) {
            val other = 1.shl(options.size - i)
            if (rightKey.and(other) == other && other == userKey.and(other)) {
                //  2021/2/22 既是正确项又是用户项，标记为 绿色+✔
                println("既是正确项又是用户项，标记为 绿色+✔")
            } else if (rightKey.and(other) == other) {
                println("用户漏选的正确项标记为 绿色")
            } else if (other == userKey.and(other)) {
                //  2021/2/22 用户选择的非正确项，标记为 红色+❌
                println("用户选择的非正确项，标记为 红色+❌")
            } else {
                //  2021/2/22 非正确项且用户未选择，不做处理
                println("非正确项且用户未选择，不做处理")
            }
        }

    }

    /**
     * 比对选项是否正确
     * @param rightKey Int
     * @param userKey Int
     * @param index Int
     */
    fun checkResult2(rightKey: Int, userKey: Int, index: Int) {
        val options = arrayListOf<String>()

        for (i in options.indices) {
            val andRight = rightKey.shr(i).and(1)
            val andUser = userKey.shr(i).and(1)
            if (andRight == 1 && andUser == 1) {
                // 2021/2/22 这个选项 既是正确项又是用户项，标记为 绿色+✔
                println("既是正确项又是用户项，标记为 绿色+✔")
            } else if (andRight == 1 || andUser == 1) {
                // 2021/2/22 这个选项 是 用户漏选的正确项，标记为 绿色
                println("这个选项 是 用户漏选的正确项，标记为 绿色")
            } else if (andRight == 1 || andUser == 1) {
                // 2021/2/22 这个选项 是 用户选择的非正确项，标记为 红色+❌
                println("这个选项 是 用户选择的非正确项，标记为 红色+❌")
            } else {
                //  2021/2/22 这个选项是 非正确项且用户未选择，不做处理
                println("这个选项是 非正确项且用户未选择，不做处理")
            }
        }

    }


}