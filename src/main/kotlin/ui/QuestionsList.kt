package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.Question

@Composable
@Preview
fun QuestionsList(
    questions: List<Question>,
    selectedAnswers: Map<String, String>,
    selectAnswer: (String, String) -> Unit
) {
    questions.map { q ->
        Text(q.question)
        q.answers.map { answer ->
            Row(
                Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = selectedAnswers.containsKey(q.question) && (selectedAnswers[q.question] == answer),
                    onClick = {
                        selectAnswer(q.question, answer)
                    })
                Text(
                    text = answer,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
                if (q.selectedAnswer == answer && q.selectedAnswer != q.correctAnswer) Icon(
                    Icons.Filled.Close, contentDescription = "Wrong answer", tint = Color.Red
                )
                if (q.selectedAnswer == answer && q.selectedAnswer == q.correctAnswer) Icon(
                    Icons.Filled.Check, contentDescription = "Correct answer", tint = Color.Green
                )
            }
        }
    }
}