package control

import getAnsweredQuestionList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ResultBuilderTest {

    val sut = ResultBuilder()

    @Test
    fun `buildResult fromQuestion result`() {
        val questions = getAnsweredQuestionList()

        val result = sut.buildResult(questions, 1)

        assertEquals(1, result.correctAnswers)
        assertEquals(2, result.totalQuestions)
        assertEquals(questions, result.questions)
    }
}
