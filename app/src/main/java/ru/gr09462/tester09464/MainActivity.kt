package ru.gr09462.tester09464

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.gr09462.tester09464.ui.TestCard
import ru.gr09462.tester09464.ui.theme.Tester09464Theme

class MainActivity : ComponentActivity() {
    private val vm by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tester09464Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .imePadding()
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Тест по французской лексике",
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = if (vm.isFinished) {
                                "Попытка завершена"
                            } else {
                                "Вопрос ${vm.currentCardNumber + 1} из ${vm.maxCardCount}. Переведите слово на русский"
                            },
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        val visibleCards = vm.cards.take(
                            (vm.currentCardNumber + 1).coerceAtMost(vm.maxCardCount)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(visibleCards) { cardState ->
                                TestCard(
                                    cardData = cardState.card,
                                    userInput = cardState.card.userRes ?: if (cardState.isActive) vm.userInput else "",
                                    isActive = cardState.isActive && !vm.isFinished,
                                    color = cardState.color,
                                    onUserInput = vm::onUserInput,
                                    onAnswer = vm::onAnswer,
                                )
                            }
                        }
                        if (vm.isFinished) {
                            Text(
                                text = "Результат: ${vm.score} из ${vm.maxCardCount} баллов",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                            Button(
                                onClick = vm::startNewAttempt,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            ) {
                                Text("Начать новую попытку")
                            }
                        } else {
                            Text(
                                text = "Набрано баллов: ${vm.score}",
                                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
