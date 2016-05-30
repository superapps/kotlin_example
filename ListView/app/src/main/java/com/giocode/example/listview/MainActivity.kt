package com.giocode.example.listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val DATA_COUNT = 100
    private val adapter: CustomAdapter by lazy { CustomAdapter(dataSet) }
    private val dataSet: Array<String?> by lazy {
        arrayOfNulls<String>(DATA_COUNT).apply {
            for (i in 0..DATA_COUNT - 1) {
                if (i % 2 == 0) set(i, "This is element #$i")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView : RecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}

fun AppCompatActivity.toastForShortTime(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
