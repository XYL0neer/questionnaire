package control

import model.Question

class AnswerHandler {
    fun evaluateAnswers(questions: List<Question>): Int {
        return questions.count { question -> question.correctAnswer == question.selectedAnswer }
    }

    fun setAnswer(question: Question, answer: String): Question {
        question.selectedAnswer = answer
        return question
    }
}