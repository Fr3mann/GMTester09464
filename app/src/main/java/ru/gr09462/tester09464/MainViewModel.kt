package ru.gr09462.tester09464

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var userInput by mutableStateOf("")
    val cards = mutableStateListOf<CardState>()
    var currentCardNumber by mutableIntStateOf(0)

    val maxCardCount = 10
    val isFinished: Boolean
        get() = currentCardNumber >= maxCardCount
    val score: Int
        get() = cards.count { it.card.isAnswerCorrect == true }

    init {
        initCards()
    }

    private fun initCards() {
        cards.clear()
        FrenchQuestionDatabase.questions.shuffled().take(maxCardCount).forEach { question ->
            cards.add(CardState(CardData(question)))
        }
        currentCardNumber = 0
        userInput = ""
    }

    fun onUserInput(input: String) {
        if (!isFinished && input.length <= 100 &&
            input.matches(Regex("[a-zA-Zа-яА-ЯёЁ ]*"))
        ) {
            userInput = input
        }
    }

    fun onAnswer() {
        if (isFinished || currentCardNumber !in cards.indices) return
        cards[currentCardNumber].card.userRes = userInput.trim()
        currentCardNumber++
        userInput = ""
    }

    fun startNewAttempt() {
        initCards()
    }
}