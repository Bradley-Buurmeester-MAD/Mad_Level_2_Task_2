package com.example.madlevel2task2

data class Question (
    var questionText: String,
    var questionAnswer: Boolean
) {
    companion object {
        val QUESTION_TEXTS = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTS.",
            "A Unit in Kotlin corresponds to a void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in java."
        )

        val QUESTION_ANSWERS = arrayOf(
            false,
            true,
            true,
            true
        )
    }
}