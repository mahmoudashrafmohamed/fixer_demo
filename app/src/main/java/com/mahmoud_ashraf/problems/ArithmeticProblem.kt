package com.mahmoud_ashraf.problems

class ArithmeticProblem {

    fun solve() {
        println((2.25 + 4.50) / 1.25 * 5)
    }

    class Main {
        companion object {
            @JvmStatic
            fun main(a: Array<String>) {
                ArithmeticProblem().solve()
            }
        }
    }
}