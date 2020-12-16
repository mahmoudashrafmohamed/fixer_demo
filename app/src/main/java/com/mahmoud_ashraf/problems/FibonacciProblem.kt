package com.mahmoud_ashraf.problems

class FibonacciProblem {

    fun fibonacci(n: Int): Int {
        val f = IntArray(n + 2)
        f[0] = 0
        f[1] = 1
        var i = 2

        while (i <= n) {
            f[i] = f[i - 1] + f[i - 2]
            i++
        }
        return f[n]
    }

    class Main {
        companion object {
            @JvmStatic
            fun main(a: Array<String>) {
                println(
                    FibonacciProblem().fibonacci(5)
                )

            }
        }
    }

}