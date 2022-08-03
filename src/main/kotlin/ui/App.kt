package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import control.QuestionManager
import exceptions.FileException
import exceptions.QuestionNotFound
import model.Question
import model.Result

const val UNKNOWN_ERROR_MSG = "An unknown error occured"

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun App(closeApp: () -> Unit) {
    val questionManager = QuestionManager()

    val openErrorDialog = remember { mutableStateOf(false) }
    val errorDialogContent = remember { mutableStateOf("") }
    val questions = remember {
        mutableStateListOf<Question>().apply {
            val questions = try {
                questionManager.start()
            } catch (ex: FileException) {
                println("Catched File Exception!!!")
                openErrorDialog.value = true
                errorDialogContent.value = ex.message ?: "An unknown error occurred"
                emptyList()
            }
            addAll(questions)
        }
    }
    val (result, setResult) = remember { mutableStateOf<Result?>(null) }
    val selectedAnswers by remember { derivedStateOf { mutableStateMapOf<String, String>() } }

    val selectAnswer = { question: String, answer: String ->
        selectedAnswers[question] = answer
    }

    val exitProgram = {
        println("should exit program")
        closeApp()
    }

    MaterialTheme {
        Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
            if (openErrorDialog.value) {
                AlertDialog(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        .widthIn(min = 256.dp, max = 1024.dp),
                    title = { Text(text = "Error Occurred") },
                    text = { Text(errorDialogContent.value) },
                    onDismissRequest = exitProgram,
                    buttons = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) { Button(onClick = exitProgram) { Text(text = "Close") } }
                    }
                )
            }
            if (result != null) ResultText(result)
            QuestionsList(questions, selectedAnswers, selectAnswer)

            Button(onClick = {
                try {
                    val newResult = questionManager.showResult(selectedAnswers)
                    questions.clear()
                    questions.addAll(newResult.questions)
                    setResult(newResult)
                } catch (ex: QuestionNotFound) {
                    openErrorDialog.value = true
                    errorDialogContent.value = ex.message ?: UNKNOWN_ERROR_MSG
                }
            }, enabled = result == null && selectedAnswers.size == questions.size) {
                Text("Show Result!")
            }
        }
    }
}