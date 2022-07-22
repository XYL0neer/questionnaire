package interfaces

import model.Question
import model.Result

interface IQuestionnaire {
    fun start(): List<Question>

    fun showResult(selectedAnswers: Map<String, String>): Result
}