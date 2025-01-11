package com.example.e_learning

class Model(
    var id: Int,
    var questionText: String,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var answer: Int
)
{
    fun getAnswerText(): String {
        return when (answer) {
            1 -> option1
            2 -> option2
            3 -> option3
            4 -> option4
            else -> ""
        }
    }
}
