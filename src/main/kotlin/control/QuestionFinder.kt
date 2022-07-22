package control

import exceptions.QuestionNotFound
import model.Question

class QuestionFinder {

    fun findQuestion(questions: List<Question>, question: String): Question {
        return questions.find { q -> q.question == question } ?: throw QuestionNotFound(question)
    }
}
