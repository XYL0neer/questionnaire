package control

import getLines
import getQuestionList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class QuestionBuilderTest {

    val sut = QuestionBuilder()
    val lines = getLines()

    val questionStartIndex = listOf(0, 4)

    @Test
    fun `detectQuestions findQuestionInLines indexesOfQuestionStarts`() {
        val result = sut.detectQuestions(lines)

        assertEquals(questionStartIndex, result)
    }

    @Test
    fun `parseQuestions readQuestionLine returnsListOfQuestions`() {
        val result = sut.parseQuestions(lines, questionStartIndex)

        assertEquals(getQuestionList(), result)
    }

    @Test
    fun `detectCorrectAnswer forFirstQuestion returnsCat`() {
        val result = sut.detectCorrectAnswer(lines.subList(0, 4))

        assertEquals("Cat", result)
    }

    @Test
    fun `removeAnswerPrefix removeCorrectAnswerPrefix`() {
        val answers = lines.subList(1, 4).toMutableList()
        sut.removeAnswerPrefix(answers)

        assertEquals(listOf("Ant", "Bee", "Cat"), answers)
    }


}