package exceptions

class QuestionNotFound(val question: String) : Exception("Question $question not found")
