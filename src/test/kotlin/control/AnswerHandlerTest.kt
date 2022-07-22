package control

import getAnsweredQuestionList
import getSingleQuestion
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AnswerHandlerTest {

    val sut = AnswerHandler()
    val questions = getAnsweredQuestionList()

    @Test
    fun `evaluateAnswers finishedQuestion CountedCorrectAnswers`() {
        val result = sut.evaluateAnswers(questions)

        assertEquals(1, result)
    }

    @Test
    fun `setAnswer finishedQuestion CountedCorrectAnswers`() {
        val q = getSingleQuestion()
        val selectedAnswer = "5"

        val result = sut.setAnswer(q, selectedAnswer)

        assertEquals("5", result.selectedAnswer)
    }
}