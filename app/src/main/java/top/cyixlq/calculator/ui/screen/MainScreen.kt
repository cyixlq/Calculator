package top.cyixlq.calculator.ui.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.cyixlq.calculator.ui.theme.GrayA5
import top.cyixlq.calculator.ui.theme.Orange

@Composable
fun Calculator(text: String, onClick: (String) -> Unit) {
    OrientationCalculator(text) {
        val btnSize = if (it == Orientation.Horizontal) 78.dp else 80.dp
        Row(Modifier.fillMaxWidth()) {
            CalculatorButton(text = "+", bgColor = Orange, modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("+")
            }
            CalculatorButton(text = "1", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("1")
            }
            CalculatorButton(text = "2", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("2")
            }
            CalculatorButton(text = "3", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("3")
            }
        }
        Row(Modifier.fillMaxWidth()) {
            CalculatorButton(text = "-", bgColor = Orange, modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("-")
            }
            CalculatorButton(text = "4", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("4")
            }
            CalculatorButton(text = "5", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("5")
            }
            CalculatorButton(text = "6", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("6")
            }
        }
        Row(Modifier.fillMaxWidth()) {
            CalculatorButton(text = "×", bgColor = Orange, modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("*")
            }
            CalculatorButton(text = "7", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("7")
            }
            CalculatorButton(text = "8", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("8")
            }
            CalculatorButton(text = "9", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("9")
            }
        }
        Row(Modifier.fillMaxWidth()) {
            CalculatorButton(text = "÷", bgColor = Orange, modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("/")
            }
            CalculatorButton(text = "AC", bgColor = GrayA5, textColor = Color.Black,
                textSize = if(it == Orientation.Horizontal) 20.sp else 22.sp,
                textWeight = FontWeight.Bold, modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("ac")
            }
            CalculatorButton(text = "0", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("0")
            }
            CalculatorButton(text = "＝", modifier = Modifier.weight(1f), btnSize = btnSize) {
                onClick("=")
            }
        }
    }
}

@Composable
fun OrientationCalculator(text: String, content: @Composable (Orientation) -> Unit) {
    BoxWithConstraints {
        if (maxWidth < 400.dp) {
            Column(Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f))
                ResultText(text = text, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp))
                content(Orientation.Vertical)
            }
        } else {
            Row(Modifier.fillMaxSize()) {
                ResultText(text = text, modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 5.dp))
                Box(modifier = Modifier.weight(1f))
                Column(Modifier.width(332.dp)) {
                    content(Orientation.Horizontal)
                }
            }
        }
    }
}

@Composable
fun ResultText(text: String, modifier: Modifier) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.inversePrimary,
        textAlign = TextAlign.End,
        fontSize = 50.sp,
        lineHeight = 50.sp,
        modifier = modifier
    )
}

@Composable
fun CalculatorButton(
    text: String,
    bgColor: Color = MaterialTheme.colorScheme.tertiary,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    textSize: TextUnit = 30.sp,
    textWeight: FontWeight = FontWeight.Normal,
    modifier: Modifier,
    btnSize: Dp,
    onClick: () -> Unit
) {
    Box(modifier = modifier.padding(vertical = 2.dp)) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(40.0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = bgColor),
            modifier = Modifier
                .width(btnSize)
                .height(btnSize)
                .align(Alignment.Center)
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = textSize,
                fontWeight = textWeight
            )
        }
    }
}
