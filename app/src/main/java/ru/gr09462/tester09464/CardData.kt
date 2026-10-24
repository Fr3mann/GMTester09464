package ru.gr09462.tester09464

import kotlin.random.Random

class CardData {
    val arg1: Int
    val arg2: Int
    val operation: Operation
    private val correctRes: Int
    var userRes: Int? = null
    val isAnswerCorrect: Boolean?
        get() = userRes?.let{it == correctRes}
    val task: String
        get() = "$arg1 ${operation.symb} ${if (arg2 < 0) "($arg2)" else arg2} ="

    init {
        operation = Operation.entries.random()
        when(operation){
            Operation.PLUS,
            Operation.MINUS-> {
                arg1 = Random.nextInt(-50, 51)
                arg2 = Random.nextInt(-50, 51)
                correctRes = if (operation == Operation.PLUS) arg1 + arg2 else arg1 - arg2
            }

            Operation.TIMES -> {
                arg1 = Random.nextInt(-10, 11)
                arg2 = Random.nextInt(-10, 11)
                correctRes = arg1 * arg2
            }

            Operation.DIV -> {
                correctRes = Random.nextInt(1, 11) * setOf(-1, 1).random()
                arg2 = Random.nextInt(1, 11) * setOf(-1,1).random()
                arg1 = correctRes * arg2
            }
        }
    }
}