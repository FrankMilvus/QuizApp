package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val SCORE = "correct_answers"

    fun getQuestions(): MutableList<Question> {
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            1,
            "What county those this flag belong?",
            R.drawable.italy,
            "Italy",
            "India",
            "Iran",
            "Ireland",
            1,
        )
        questions.add(quest1)

        val quest2 = Question(
            2,
            "What county those this flag belong?",
            R.drawable.argentina,
            "Italy",
            "India",
            "Argentina",
            "Ireland",
            3,
        )
        questions.add(quest2)

        val quest3 = Question(
            3,
            "What county those this flag belong?",
            R.drawable.bras,
            "brasilia",
            "India",
            "Iran",
            "Ireland",
            1,
        )
        questions.add(quest3)

        val quest4 = Question(
            4,
            "What county those this flag belong?",
            R.drawable.finland,
            "Brasilia",
            "India",
            "Finland",
            "Ireland",
            3,
        )
        questions.add(quest4)

        val quest5 = Question(
            5,
            "What county those this flag belong?",
            R.drawable.france,
            "Brasilia",
            "India",
            "France",
            "German",
            3,
        )
        questions.add(quest5)

        val quest6 = Question(
            6,
            "What county those this flag belong?",
            R.drawable.german,
            "Brasilia",
            "German",
            "Finland",
            "Ireland",
            2,
        )
        questions.add(quest6)

        val quest7 = Question(
            7,
            "What county those this flag belong?",
            R.drawable.india,
            "india",
            "German",
            "Finland",
            "Ireland",
            1,
        )
        questions.add(quest7)

        val quest8 = Question(
            8,
            "What county those this flag belong?",
            R.drawable.nigeria,
            "india",
            "German",
            "Finland",
            "Nigeria",
            4,
        )
        questions.add(quest8)

        val quest9 = Question(
            9,
            "What county those this flag belong?",
            R.drawable.ramania,
            "india",
            "German",
            "Ramania",
            "Nigeria",
            3,
        )
        questions.add(quest9)

        val quest10 = Question(
            10,
            "What county those this flag belong?",
            R.drawable.spain,
            "india",
            "Spain",
            "Ramania",
            "Nigeria",
            2,
        )
        questions.add(quest10)


        return questions
    }
}