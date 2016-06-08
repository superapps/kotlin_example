package com.linecorp.memo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class MemoListActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_list)

        val memoDao = MemoDao()
        val adapter = MemoListAdapter(this, memoDao)
        val recyclerView = findViewById(R.id.memolist_recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val titleInputView = findViewById(R.id.memolist_input_title) as EditText
        val contentInputView = findViewById(R.id.memolist_input_content) as EditText
        findViewById(R.id.memolist_add_button).setOnClickListener {
            memoDao.add(Memo(createdTime = System.currentTimeMillis(), title = titleInputView.text.toString(), content = contentInputView.text.toString()))
            adapter.notifyDataSetChanged()
        }

        findViewById(R.id.memolist_sort_by_title_button).setOnClickListener {
            memoDao.sortByTitle();
            adapter.notifyDataSetChanged()
        }

        findViewById(R.id.memolist_sort_by_created_time_button).setOnClickListener {
            memoDao.sortByCreatedTime();
            adapter.notifyDataSetChanged()
        }
    }

    class MemoListAdapter(val context: Context, val memoDao: MemoDao) : RecyclerView.Adapter<MemoViewHolder>() {

        override fun getItemCount(): Int {
            return memoDao.list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MemoViewHolder? {
            return MemoViewHolder(LayoutInflater.from(context).inflate(R.layout.memo_list_item, null))
        }

        override fun onBindViewHolder(holder: MemoViewHolder?, position: Int) {
            holder?.view?.setOnClickListener {
                memoDao.remove(position)
                notifyDataSetChanged()
            }
            holder?.titleView?.text = memoDao.list.get(position).title
            holder?.contentView?.text = memoDao.list.get(position).content
            holder?.createdTime?.text = memoDao.list.get(position).createdTime.toString()
        }
    }

    class MemoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.findViewById(R.id.memo_list_item_title) as TextView
        val contentView = view.findViewById(R.id.memo_list_item_content) as TextView
        val createdTime = view.findViewById(R.id.memo_list_item_created_time) as TextView
    }
}


