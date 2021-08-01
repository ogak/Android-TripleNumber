package com.example.triplex

import android.util.Log


class GameFactory(levelDifficulty: Int) {
    private var numRange = 1..levelDifficulty
    private var codeA = numRange.random() % levelDifficulty + levelDifficulty
    private var codeB = numRange.random() % levelDifficulty + levelDifficulty
    private var codeC = numRange.random() % levelDifficulty + levelDifficulty

    fun logRandom() {
        Log.d("Number 1 = ", "$codeA")
        Log.d("Number 2 = ", "$codeB")
        Log.d("Number 3 = ", "$codeC")
    }


    fun sumCode(): Int {
        return codeA + codeB + codeC
    }

    fun multiplyCode(): Int {
        return codeA * codeB * codeC
    }

    fun codeCheck(num1: Int, num2: Int, num3: Int): Boolean {
        val inputSum = num1 + num2 + num3
        val inputMultiply = num1 * num2 * num3

        return inputSum == sumCode().toString()
            .toInt() && inputMultiply == multiplyCode().toString().toInt()
    }


}


