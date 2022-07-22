// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import control.QuestionManager
import model.Question
import model.Result

fun percentageString(numerator: Int, denominator: Int) = ((numerator / denominator) * 100).toString() + "%"

@Composable
@Preview
fun App() {
    val questionManager = QuestionManager()

    val questions = remember { mutableStateListOf<Question>().apply { addAll(questionManager.start()) } }
    val (result, setResult) = remember { mutableStateOf<Result?>(null) }
    val selectedAnswers by remember { derivedStateOf { mutableStateMapOf<String, String>() } }

    MaterialTheme {
        Column {
            if (result != null) Text(
                "${result.correctAnswers} out of ${result.totalQuestions} questions answered correctly (${
                    percentageString(
                        result.correctAnswers, result.totalQuestions
                    )
                })", modifier = Modifier.padding(bottom = 16.dp)
            )
            questions.map { q ->
                Text(q.question)
                q.answers.map { answer ->
                    Row(
                        Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = selectedAnswers.containsKey(q.question) && (selectedAnswers[q.question] == answer),
                            onClick = {
                                selectedAnswers[q.question] = answer
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
            Button(onClick = {
                val newResult = questionManager.showResult(selectedAnswers)
                questions.clear()
                questions.addAll(newResult.questions)
                setResult(newResult)
            }, enabled = result == null && selectedAnswers.size == questions.size) {
                Text("Show Result!")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
