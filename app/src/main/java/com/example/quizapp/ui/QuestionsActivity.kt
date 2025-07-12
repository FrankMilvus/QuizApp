package com.example.quizapp.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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

    private val currentPosition = 1
    private lateinit var questionsList: MutableList<Question>
    private lateinit var selectedOptionPosition = 0

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

        buttonCheck= findViewById(R.id.button_check)

        questionsList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")
        setQuestion()
    }

    private fun setQuestion (){
        val question = questionsList[currentPosition-1]
        flagImage.setImageResource(question.image)
        progressBar.progress=currentPosition
        textViewProgress.text ="$currentPosition/${progressBar.max}"
        textViewQuestion.text = question.question
        textViewOption1.text=question.optionOne
        textViewOption2.text=question.optionTwo
        textViewOption3.text=question.optionThree
        textViewOption4.text=question.optionFour

        if (currentPosition == questionsList.size){
            buttonCheck.text= getString(R.string.buttomn_check_finish)
        } else {
            buttonCheck.text = getString(R.string.button_check_check)
        }

    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOption1)
        options.add(textViewOption2)
        options.add(textViewOption3)
        options.add(textViewOption4)

        for (option in options){
            option.setTextColor((Color.parseColor("#7A8089")))
            option.typeface= Typeface.DEFAULT
            option.background= ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }


    override fun onClick(view: View?) {
        when(view.id){

        }
    }
}












