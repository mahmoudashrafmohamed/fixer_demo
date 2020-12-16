package com.mahmoud_ashraf.problems

import java.util.*


class AnagramsProblem {

    fun isAnagram(str1: String, str2: String): Boolean {
        if (str1.length != str2.length) return false
        val arr1 = str1.toCharArray()
        val arr2 = str2.toCharArray()
        Arrays.sort(arr1)
        Arrays.sort(arr2)
        return arr1.contentEquals(arr2)
    }

    class Main {
        companion object {
            @JvmStatic
            fun main(a: Array<String>) {
                println(
                    "is anagram " + AnagramsProblem().isAnagram("debit card", "bad credit")
                )

            }
        }
    }
}