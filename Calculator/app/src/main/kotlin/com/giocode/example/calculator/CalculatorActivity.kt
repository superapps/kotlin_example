package com.giocode.example.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView

class CalculatorActivity : AppCompatActivity() {

    val history = History()
    val resultText: TextView by lazy {
        findViewById(R.id.calculator_result) as TextView
    }
    val edit: EditText by lazy {
        findViewById(R.id.calculator_edit) as EditText
    }
    val listView: RecyclerView by lazy {
        findViewById(R.id.calculator_history) as RecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        title = "Calculator by Kotlin"

        initEditText()

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = HistoryAdapter(this, history)

        history.setOnDataChangeListener {
            listView?.adapter.notifyDataSetChanged()
        }
    }

    private fun initEditText() {
        edit.filters = arrayOf(InputFilter {
            source, start, end, dest, dStart, dEnd ->
            val s = source.subSequence(start, end).toString()
            if (s.contains("=")) {
                addResult();
                return@InputFilter s remove "="
            }
            return@InputFilter source
        })
        edit.setOnEditorActionListener({
            textView, actionId, keyEvent ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_NEXT,
                EditorInfo.IME_ACTION_GO,
                EditorInfo.IME_ACTION_SEARCH,
                EditorInfo.IME_ACTION_SEND -> addResult()

            }
            false
        })

        edit.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                resultText.text = Calculator.calculate(text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun addResult() {
        history.add(Calculator.result.copy())
        edit.setText(Calculator.result.value.toString())
    }
}