package control

import model.Question

class QuestionBuilder {
    fun buildQuestion(lines: List<String>): List<Question> {
        val questionStartIndexes = detectQuestions(lines)
        return parseQuestions(lines, questionStartIndexes)
    }

    fun detectQuestions(lines: List<String>): List<Int> {
        val questionStartIndex = mutableListOf<Int>()
        lines.forEachIndexed { index, line ->
            if (line.startsWith("?")) {
                questionStartIndex.add(index)
            }
        }
        return questionStartIndex
    }

    fun parseQuestions(lines: List<String>, questionIndexes: List<Int>): List<Question> {
        val questions = mutableListOf<Question>()
        for (i in questionIndexes.indices) {
            var questionEndIndex = lines.size
            try {
                questionEndIndex = questionIndexes[i+1]
            } catch (ex: java.lang.IndexOutOfBoundsException) {
                println("out of bounds but catched")
            }
            val questionLines = lines.subList(questionIndexes[i], questionEndIndex)
            val questionLine = questionLines.first().substringAfter("?")
            val answers = questionLines.takeLast(questionLines.size - 1).toMutableList()
            val correctAnswers = detectCorrectAnswer(answers) ?: continue
            removeAnswerPrefix(answers)

            val question = Question(questionLine, answers, correctAnswers, null)
            questions.add(question)
        }
        return questions
    }

    fun detectCorrectAnswer(lines: List<String>): String? {
        return lines.find { line -> line.startsWith("*") }?.removePrefix("*")
    }

    fun removeAnswerPrefix(answers: MutableList<String>) {
        for (i in answers.indices) {
            answers[i] = answers[i].removePrefix("*")
        }
    }

}