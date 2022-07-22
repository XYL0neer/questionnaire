package control

import exceptions.QuestionNotFound
import getAnsweredQuestionList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class QuestionFinderTest {
    val sut = QuestionFinder()

    @Test
    fun `findQuestion findExisitingQuestion returnsQuestion`() {
        val questions = getAnsweredQuestionList()
        val questionToFind = "What is the sum of 2+3"

        val result = sut.findQuestion(questions, questionToFind)

        assertEquals(questionToFind, result.question)
    }

    @Test
    fun `findQuestion findNonExisitingQuestion throwsNotFoundExceptions`() {
        val questions = getAnsweredQuestionList()
        val questionToFind = "Cant find this"

        assertThrows(QuestionNotFound::class.java) {
            sut.findQuestion(questions, questionToFind)
        }
    }
}
