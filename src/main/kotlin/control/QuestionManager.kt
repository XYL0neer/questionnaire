package control

import interfaces.IQuestionnaire
import model.Question
import model.Result
import provider.FileProvider

class QuestionManager() : IQuestionnaire {

    constructor(questions: MutableList<Question>) : this() {
        this.questions = questions
    }

    private var questions = mutableListOf<Question>()

    private val fileProvider = FileProvider()
    private val questionBuilder = QuestionBuilder()
    private val questionFinder = QuestionFinder()
    private val answerHandler = AnswerHandler()
    private val resultBuilder = ResultBuilder()


    override fun start(): List<Question> {
        val lines = fileProvider.readFile()
        questions.addAll(questionBuilder.buildQuestion(lines))
        return questions
    }

    override fun showResult(selectedAnswers: Map<String, String>): Result {
        setAnswers(selectedAnswers)
        val correctAnswers = answerHandler.evaluateAnswers(questions)
        return resultBuilder.buildResult(questions, correctAnswers)
    }

    private fun setAnswers(selectedAnswers: Map<String, String>) {
        for ((question, answer) in selectedAnswers) {
            try {
                val q = questionFinder.findQuestion(questions, question)
                answerHandler.setAnswer(q, answer)
            } catch (ex: IllegalArgumentException) {
                println(ex.message)
            }
        }
    }
}
