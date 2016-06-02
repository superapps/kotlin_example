package com.giocode.example.listview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.giocode.example.calculator.CalculatorActivity

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

        var recyclerView: RecyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_calculator -> startActivity(Intent(this, CalculatorActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}

fun AppCompatActivity.toastForShortTime(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
