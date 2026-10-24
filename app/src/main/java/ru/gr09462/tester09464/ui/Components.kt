package ru.gr09462.tester09464.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.gr09462.tester09464.CardData
import ru.gr09462.tester09464.R
import ru.gr09462.tester09464.ui.theme.Tester09464Theme

@Composable
fun TestCard(
    cardData: CardData,
    userInput: String,
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
    color: Color = Color.Transparent,
    onUserInput: (String) -> Unit = {},
    onAnswer: () -> Unit = {},
){

    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = color
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    cardData.task,
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center,
                    fontSize = 38.sp
                )
                OutlinedTextField(
                    value = userInput,
                    onValueChange = onUserInput,
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    ),
                    enabled = isActive,
                )
            }
            IconButton(
                onClick = onAnswer,
                enabled = isActive,
            ){
                Icon(
                    painter = painterResource(R.drawable.set_answer_32),
                    contentDescription = null,
                    tint = if (isActive) Color.Green else Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun TestCardPreview(){
    val c = CardData()
    Tester09464Theme(
        darkTheme = true
    ){
        TestCard(c, "")
    }
}