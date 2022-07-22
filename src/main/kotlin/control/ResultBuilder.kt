package control

import model.Question
import model.Result

class ResultBuilder {
    fun buildResult(questions: List<Question>, correctAnswersCount: Int): Result {
        return Result(questions, questions.size, correctAnswersCount)
    }
}