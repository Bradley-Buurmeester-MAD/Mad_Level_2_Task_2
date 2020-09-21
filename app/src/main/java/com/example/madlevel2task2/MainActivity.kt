package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        for (i in Question.QUESTION_TEXTS.indices) {
            questions.add(Question(Question.QUESTION_TEXTS[i], Question.QUESTION_ANSWERS[i]))
        }

        questionAdapter.notifyDataSetChanged()

        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    /**
     *
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        val ANSWER_TRUE = true;
        val ANSWER_FALSE = false;

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val awnser = questions[position].questionAnswer
                var submittedAnswer : Boolean

                if (direction == ItemTouchHelper.RIGHT){
                    submittedAnswer = ANSWER_TRUE
                } else{
                    submittedAnswer = ANSWER_FALSE
                }

                if (awnser == submittedAnswer) {
                    Snackbar.make(rvQuestions, R.string.Correct, Snackbar.LENGTH_LONG).show()
                } else {
                    Snackbar.make(rvQuestions, R.string.Incorrect, Snackbar.LENGTH_LONG).show()
                }

                questions.removeAt(position)
                questionAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}