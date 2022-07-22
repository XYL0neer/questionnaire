import model.Question

fun getQuestionList() = listOf(
    Question("Which of these animals is a mammal", listOf("Ant", "Bee", "Cat"), "Cat", null),
    Question("What is the sum of 2+3", listOf("2", "5", "6"), "5", null)
)

fun getAnsweredQuestionList() = listOf(
    Question("Which of these animals is a mammal", listOf("Ant", "Bee", "Cat"), "Cat", "Bee"),
    Question("What is the sum of 2+3", listOf("2", "5", "6"), "5", "5")
)

fun getLines() = listOf(
    "?Which of these animals is a mammal",
    "Ant",
    "Bee",
    "*Cat",
    "?What is the sum of 2+3",
    "2",
    "*5",
    "6",
)

fun getSingleQuestion() = Question("What is the sum of 2+3", listOf("2", "5", "6"), "5", null)