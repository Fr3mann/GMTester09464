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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Column(modifier = Modifier.padding(innerPadding)) {
                        if (vm.currentCardNumber < vm.maxCardCount) {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .weight(1f),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                items(vm.cards.slice(0..vm.currentCardNumber % vm.maxCardCount)) {
                                    TestCard(
                                        it.card,
                                        it.card.userRes?.toString() ?: vm.userInput,
                                        isActive = it.isActive,
                                        color = it.color,
                                        onUserInput = vm::onUserInput,
                                        onAnswer = vm::onAnswer,
                                    )
                                }
                            }
                        }
                        else {
                            Text("Тест финишд")
                        }
                    }
                }
            }
        }
    }
}
