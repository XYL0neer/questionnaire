package control

import model.Question

const val QUESTION_PREFIX = "?"
const val CORRECT_ANSWER_PREFIX = "*"

class QuestionBuilder {
    fun buildQuestion(lines: List<String>): List<Question> {
        val questionStartIndexes = detectQuestions(lines)
        return parseQuestions(lines, questionStartIndexes)
    }

    fun detectQuestions(lines: List<String>): List<Int> {
        val questionStartIndex = mutableListOf<Int>()
        lines.forEachIndexed { index, line ->
            if (line.startsWith(QUESTION_PREFIX)) {
                questionStartIndex.add(index)
            }
        }
        return questionStartIndex
    }

    fun parseQuestions(lines: List<String>, questionIndexes: List<Int>): List<Question> {
        val questions = mutableListOf<Question>()
        for (i in questionIndexes.indices) {
            var questionEndIndex = lines.size
            if (i < questionIndexes.lastIndex) {
                questionEndIndex = questionIndexes[i + 1]
            }
            val questionLines = lines.subList(questionIndexes[i], questionEndIndex)
            val questionLine = questionLines.first().substringAfter("?")
            val answers = questionLines.takeLast(questionLines.size - 1).toMutableList()
            val correctAnswer = detectCorrectAnswer(answers) ?: continue
            removeAnswerPrefixInAnswersOptions(answers)
            addDontKnowOption(answers)

            val question = Question(questionLine, answers, correctAnswer)
            questions.add(question)
        }
        return questions
    }

    fun addDontKnowOption(answers: MutableList<String>) {
        answers.add("I don't know")
    }

    fun detectCorrectAnswer(lines: List<String>): String? {
        return lines.find { line -> line.startsWith(CORRECT_ANSWER_PREFIX) }?.removePrefix(CORRECT_ANSWER_PREFIX)
    }

    fun removeAnswerPrefixInAnswersOptions(answers: MutableList<String>) {
        for (i in answers.indices) {
            answers[i] = answers[i].removePrefix(CORRECT_ANSWER_PREFIX)
        }
    }
}
