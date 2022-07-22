package control

import getAnsweredQuestionList
import getQuestionList
import model.Result
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QuestionManagerTest {

    val selectedAnswers = mapOf("Which of these animals is a mammal" to "Bee", "What is the sum of 2+3" to "5")

    @Test
    fun `start loadsQuestions`() {
        val sut = QuestionManager()

        val result = sut.start()

        assertEquals(getQuestionList(), result)
    }

    @Test
    fun `showResults generatesResult`() {
        val sut = QuestionManager(getQuestionList().toMutableList())
        val expected = getAnsweredQuestionList()

        val result = sut.showResult(selectedAnswers)

        assertEquals(Result(expected, expected.size, 1), result)
    }
}
