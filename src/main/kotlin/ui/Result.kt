package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Result
import kotlin.math.roundToInt

fun percentageString(numerator: Double, denominator: Double) = ((numerator / denominator) * 100).roundToInt().toString() + "%"

@Composable
@Preview
fun ResultText(result: Result) {
    Text(
        "${result.correctAnswers} out of ${result.totalQuestions} questions answered correctly (${
            percentageString(
                result.correctAnswers.toDouble(), result.totalQuestions.toDouble()
            )
        })", modifier = Modifier.padding(bottom = 16.dp)
    )
}