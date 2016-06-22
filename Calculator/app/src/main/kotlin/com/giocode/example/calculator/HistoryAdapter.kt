package com.giocode.example.calculator

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HistoryAdapter(val context: Context, val history: History) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun getItemCount() = history.list.size

    override fun onBindViewHolder(holder: HistoryViewHolder?, position: Int) {
        holder?.update(history.list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HistoryViewHolder? {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false)).apply {
            deleteView.setOnClickListener { history.delete(adapterPosition) }
        }
    }

    class HistoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val expressionView: TextView by lazy {
            view.findViewById(R.id.history_expression) as TextView
        }

        val valueView: TextView by lazy {
            view.findViewById(R.id.history_value) as TextView
        }

        val deleteView: View by lazy {
            view.findViewById(R.id.history_delete)
        }

        fun update(result: Result) {
            expressionView.text = result.expression
            valueView.text = result.value.toString()
        }
    }
}
