package ru.gr09462.tester09464

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var userInput by mutableStateOf("")
    val cards = mutableStateListOf<CardState>()
    var currentCardNumber by mutableIntStateOf(0)

    val maxCardCount = 5

    init {
        initCards()
    }

    private fun initCards(){
        cards.clear()
        repeat(maxCardCount){
            cards.add(CardState())
        }
        currentCardNumber = 0
    }

    fun onUserInput(input: String) {
        input.toIntOrNull()?.let {
            if (it in -100..100)
                userInput = input
        } ?: run {
            if (input.isEmpty() || input == "-")
                userInput = input
        }
    }

    fun onAnswer (){
        cards[currentCardNumber].card.userRes = userInput.toIntOrNull()
        currentCardNumber ++
        userInput = ""
    }

}