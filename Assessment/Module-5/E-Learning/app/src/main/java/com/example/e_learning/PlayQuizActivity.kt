package com.example.e_learning

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class PlayQuizActivity : AppCompatActivity() {

    lateinit var questionTextView: TextView
    lateinit var optionsRadioGroup: RadioGroup
    lateinit var submitButton: Button
    lateinit var scoreTextView: TextView
    lateinit var closebutton:Button
    var currentQuestionIndex = 0
    var score = 0
    lateinit var questions: List<Model>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_quiz)

        window.statusBarColor = Color.rgb(75,0,130)

        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        submitButton = findViewById(R.id.submitButton)
        closebutton = findViewById(R.id.closebtn)
        scoreTextView = findViewById(R.id.scoreTextView)

        var databaseHelper = DatabaseHelper(this)
        questions = databaseHelper.readAllQuestions()

        if (questions.isNotEmpty()) {
            displayQuestion(currentQuestionIndex)
        }
        scoreTextView.text = "$score"

        submitButton.setOnClickListener {
            var selectedOptionId = optionsRadioGroup.checkedRadioButtonId
            var selectedRadioButton = findViewById<RadioButton>(selectedOptionId)

            if (selectedRadioButton == null) {
                Toast.makeText(applicationContext, "Please select an option !!!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var selectedAnswer = selectedRadioButton.text.toString()
            var correctAnswer = questions[currentQuestionIndex].getAnswerText()

            var soundHelper = SoundHelper(this)
            if (selectedAnswer == correctAnswer) {
                score++
                soundHelper.playSound(true)
                Toast.makeText(applicationContext, "Correct Answer!", Toast.LENGTH_SHORT).show()
            }
            else {
                soundHelper.playSound(false)
                highlightCorrectAnswer(correctAnswer)
                Toast.makeText(applicationContext, "Wrong Answer!", Toast.LENGTH_LONG).show()
            }

            scoreTextView.text = "$score"


            Handler(Looper.getMainLooper()).postDelayed({
                currentQuestionIndex++

                if (currentQuestionIndex < questions.size) {

                    displayQuestion(currentQuestionIndex)

                }
                else {

                    var alertDialog = AlertDialog.Builder(this)
                        .setTitle("Quiz Completed")
                        .setMessage("Your final score is: $score\nWould you like to play again?")
                        .setPositiveButton("Yes") { dialog, _ ->
                            currentQuestionIndex = 0
                            score = 0
                            scoreTextView.text = "$score"
                            displayQuestion(currentQuestionIndex)
                            submitButton.text = "Submit"
                        }
                        .setNegativeButton("No") { _, _ -> finish() }
                        .create()

                    alertDialog.show()
                }
            }, 1500)
        }

        closebutton.setOnClickListener {

            var alertDialog = AlertDialog.Builder(this)
                .setTitle("Quiz Uncompleted !!")
                .setMessage("Your score is: $score\n Are you sure, you want to exit !!")
                .setPositiveButton("No") { dialog, _ ->
                    Toast.makeText(applicationContext, "Let's Continue...", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Yes") { _, _ -> finish() }
                .create()

            alertDialog.show()

        }

    }

    private fun displayQuestion(index: Int) {
        var question = questions[index]
        questionTextView.text = question.questionText
        var options = arrayOf(question.option1, question.option2, question.option3, question.option4)

        optionsRadioGroup.clearCheck()
        optionsRadioGroup.removeAllViews()

        for (i in options.indices) {
            var radioButton = RadioButton(this)
            radioButton.text = options[i]
            radioButton.id = View.generateViewId()
            optionsRadioGroup.addView(radioButton)
        }

        enableOptions()
    }

    private fun highlightCorrectAnswer(correctAnswer: String) {
        for (i in 0 until optionsRadioGroup.childCount) {
            var radioButton = optionsRadioGroup.getChildAt(i) as RadioButton
            if (radioButton.text.toString() == correctAnswer) {
                radioButton.setBackgroundColor(Color.rgb(177,156, 217))
                radioButton.setTextColor(Color.WHITE)
            } else {
                radioButton.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun enableOptions() {
        for (i in 0 until optionsRadioGroup.childCount) {
            var radioButton = optionsRadioGroup.getChildAt(i) as RadioButton
            radioButton.isEnabled = true
            radioButton.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        var alertDialog = AlertDialog.Builder(this)
            .setTitle("Quiz Uncompleted !!")
            .setMessage("Your score is: $score\n Are you sure, you want to exit !!")
            .setPositiveButton("No") { dialog, _ ->
                Toast.makeText(applicationContext, "Let's Continue...", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ -> finish() }
            .create()

        alertDialog.show()
    }
}
