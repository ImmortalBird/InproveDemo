package com.boolaw.myapplication;

public class Test {

    public static void main(String[] args) {
//        System.out.println("args = " + champagneTower(6,3,2));
            Solution solution = new Solution();
        for (int i = 0; i < 100; i++) {
            assert solution.fib(i) == fib(i);
            System.out.println(i + " = " + fib(i) + " 短" + (solution.fib(i)));
        }
    }

    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int r = 1, last = 1, mod = 1000000007;
        for (int i = 3; i <= n; i++) {
            r += last;
            last = r - last;
            if (r > mod) r -= mod;
        }
        return r;

    }

    public static double champagneTower(int poured, int query_row, int query_glass) {
        if (poured > Math.pow(2.0, query_row + 1.0) - 1)
            return 1.0;
        double[] row = {poured};
        for (int i = 1; i <= query_row; i++) {
            double[] nextRow = new double[i + 1];
            for (int j = 0; j < i; j++) {
                double volume = row[j];
                if (volume > 1) {
                    nextRow[j] += (volume - 1) / 2;
                    nextRow[j + 1] += (volume - 1) / 2;
                }
            }
            row = nextRow;
        }
        return Math.min(1, row[query_glass]);
    }

    static class Solution2 {
        int mod = 1000000007;

        public int fib(int n) {
            if (n < 2) return n;
            int[][] a = new int[][]{{1, 1}, {1, 0}};
            int[] m = new int[]{1, 0};
            int temp = m[0];
            n--;
            while (n > 0) {
                if ((n & 1) == 1) {
                    m[0] = (int) (((long) a[0][0] * m[0] + (long) a[0][1] * m[1]) % mod);
                    m[1] = (int) (((long) a[1][0] * temp + (long) a[1][1] * m[1]) % mod);
                    temp = m[0];
                }
                a = multiplyMatrix(a, a);
                n >>= 1;
            }
            return m[0];
        }

        public int[][] multiplyMatrix(int[][] a, int[][] b) {
            int[][] result = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    result[i][j] = (int) ((((long) a[i][0] * b[0][j]) + ((long) a[i][1] * b[1][j])) % mod);
                }
            }
            return result;
        }

    }

    static class Solution {
        int mod = 1000000007;

        public int fib(int n) {
            if (n < 2) return n;
            // 创建result数组 r
            int[] r = new int[]{1, 0};
            // 创建矩阵数组 a
            int[][] a = new int[][]{{1, 1}, {1, 0}};
            // n -- 是因为我们只需要算出 F(n)就可以，没必要多算一次求出F(n+1)
            n--;
            // 因为 r[0] 在下面的计算中会重新被赋值，所以用temp记录下来，否则会计算错误
            int temp = r[0];
            // 无限循环，终止条件在循环体内
            while (true) {
                if ((n & 1) == 1) {
                    r[0] = (int) (((long) a[0][0] * temp + ((long) a[0][1] * r[1])) % mod);
                    r[1] = (int) (((long) a[1][0] * temp + (long) a[1][1] * r[1]) % mod);
                    temp = r[0];
                    if (n == 1) break;
                }
                n >>= 1;
                a = multiply(a, a);
            }
            return r[0];
        }

        private int[][] multiply(int[][] a, int[][] b) {
            int[][] arr = new int[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    arr[i][j] = (int) (((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % mod);
                }
            }
            return arr;

        }


    }
}
