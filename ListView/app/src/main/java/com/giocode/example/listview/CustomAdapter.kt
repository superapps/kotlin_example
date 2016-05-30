package com.giocode.example.listview

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapter(val dataSet: Array<String?>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataSet.getOrNull(position)?.let {
            holder.textView.text = it
        }?:let{
            holder.textView.text = "No Item"
        }
    }

    override fun getItemCount(): Int = dataSet.size

    class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView by lazy {
            v.findViewById(R.id.textView) as TextView
        }

        init {
            val context = v.context as AppCompatActivity
            v.setOnClickListener {
                context.toastForShortTime("Element $position clicked.")
            }
        }

    }
}