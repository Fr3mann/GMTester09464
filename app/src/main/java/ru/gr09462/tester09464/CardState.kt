package ru.gr09462.tester09464

import androidx.compose.ui.graphics.Color

class CardState(
    val card: CardData,
) {
    val color: Color
        get() = card.isAnswerCorrect?.let{
            if (it)
                Color(0f, 1f, 0f, 0.15f)
            else
                Color(1f, 0f, 0f, 0.15f)
        }?: Color.Transparent
    val isActive: Boolean
        get() = card.userRes == null
}