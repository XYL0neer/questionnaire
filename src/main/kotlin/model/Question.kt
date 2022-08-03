package model

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String,
    var selectedAnswer: String? = null,
)
