package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView
    private lateinit var textViewOption1: TextView
    private lateinit var textViewOption2: TextView
    private lateinit var textViewOption3: TextView
    private lateinit var textViewOption4: TextView
    private lateinit var buttonCheck: Button


    //    private var currentPosition = 1
    private var questionCounter = 0
    private lateinit var questionsList: MutableList<Question>

    //    private var selectedOptionPosition = 0
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false

    private lateinit var name: String
    private var score: Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        progressBar = findViewById(R.id.progress_bar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.text_view_question)
        flagImage = findViewById(R.id.image_flag)

        textViewOption1 = findViewById(R.id.text_view_option_one)
        textViewOption2 = findViewById(R.id.text_view_option_two)
        textViewOption3 = findViewById(R.id.text_view_option_three)
        textViewOption4 = findViewById(R.id.text_view_option_four)



        buttonCheck = findViewById(R.id.button_check)

        textViewOption1.setOnClickListener(this)
        textViewOption2.setOnClickListener(this)
        textViewOption3.setOnClickListener(this)
        textViewOption4.setOnClickListener(this)
        buttonCheck.setOnClickListener(this)

        questionsList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")
        showNextQuestion()

        if(intent.hasExtra(Constants.USER_NAME)){
            name = intent.getStringExtra(Constants.USER_NAME)!!
        }
    }

    private fun showNextQuestion() {

        if (questionCounter < questionsList.size) {
            buttonCheck.text = getString(R.string.button_check_check)

            val question = questionsList[questionCounter]
            currentQuestion = question //added string
            flagImage.setImageResource(currentQuestion.image)//(question.image)
            progressBar.progress = questionCounter
            textViewProgress.text = "${questionCounter + 1}/${progressBar.max}" //+1

            textViewQuestion.text = question.question
            textViewOption1.text = question.optionOne
            textViewOption2.text = question.optionTwo
            textViewOption3.text = question.optionThree
            textViewOption4.text = question.optionFour
        } else {
            buttonCheck.text = getString(R.string.button_check_check)
            startActivity(Intent(this, ResultActivity::class.java).also {
                it.putExtra(Constants.USER_NAME, name)
                it.putExtra(Constants.SCORE, score)
                it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
            })


            Toast.makeText(this@QuestionsActivity, "Quiz is over!", Toast.LENGTH_SHORT).show()
        }


        answered = false
        questionCounter++
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOption1)
        options.add(textViewOption2)
        options.add(textViewOption3)
        options.add(textViewOption4)


        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOption(
        textView: TextView,
        selectOptionNumber: Int
    ) {
        resetOptions()
        selectedAnswer = selectOptionNumber
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_view_option_one -> {
                selectedOption(textViewOption1, 1)
            }

            R.id.text_view_option_two -> {
                selectedOption(textViewOption2, 2)
            }

            R.id.text_view_option_three -> {
                selectedOption(textViewOption3, 3)
            }

            R.id.text_view_option_four -> {
                selectedOption(textViewOption4, 4)
            }

            R.id.button_check -> {
                if (!answered) {
                    checkAnswer()

                } else {
                    showNextQuestion()
                    resetOptions()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {

        if (selectedAnswer == currentQuestion.correctAnswer) {
            highLightAnswer(selectedAnswer)
            answered = true
            score++
        } else {
            score--
            when (selectedAnswer) {
                1 -> {
                    textViewOption1.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                2 -> {
                    textViewOption2.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                3 -> {
                    textViewOption3.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }

                4 -> {
                    textViewOption4.background =
                        ContextCompat.getDrawable(
                            this,
                            R.drawable.wrong_option_border_bg
                        )
                }
            }
        }
        buttonCheck.text = getString(R.string.button_next)
        showSolution()

    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer
        highLightAnswer(selectedAnswer)

    }

    private fun highLightAnswer(answer: Int) {
        when (answer) {
            1 -> {
                textViewOption1.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            2 -> {
                textViewOption2.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            3 -> {
                textViewOption3.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }

            4 -> {
                textViewOption4.background =
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.correct_option_border_bg
                    )
            }
        }
    }
}












