package com.example.e_learning

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Display.Mode
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReadQuestionsActivity : AppCompatActivity() {

     lateinit var databaseHelper: DatabaseHelper
     lateinit var questionsContainer: LinearLayout
     lateinit var closeButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_questions)

        window.statusBarColor = Color.rgb(75,0,130)

        questionsContainer = findViewById(R.id.questionsContainer)
        closeButton = findViewById(R.id.closeButton)

        databaseHelper = DatabaseHelper(this)

        var questions = databaseHelper.readAllQuestions()
        if (questions.isEmpty()) {
            Toast.makeText(this, "No questions available.", Toast.LENGTH_SHORT).show()
            finish()
        }

        for ((index, question) in questions.withIndex()) {
            var questionView = createQuestionView(question, index + 1)
            questionsContainer.addView(questionView)
        }

        closeButton.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    private fun createQuestionView(question: Model, questionNumber: Int): LinearLayout {
        var questionLayout = LinearLayout(this)
        questionLayout.orientation = LinearLayout.VERTICAL
        questionLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        var questionText = TextView(this)
        questionText.text = "$questionNumber. ${question.questionText}"
        questionText.textSize = 18f
        questionText.setPadding(0, 10, 0, 10)

        var optionsText = TextView(this)
        optionsText.text = """
            A) ${question.option1}
            B) ${question.option2}
            C) ${question.option3}
            D) ${question.option4}
            Ans.: ${question.answer}
        """.trimIndent()
        optionsText.textSize = 16f
        optionsText.setPadding(0,0, 0, 20)

        questionLayout.addView(questionText)
        questionLayout.addView(optionsText)

        return questionLayout
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
