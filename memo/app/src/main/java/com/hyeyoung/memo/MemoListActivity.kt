package com.hyeyoung.memo

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
import android.widget.Toast
import kotlin.properties.Delegates

class MemoListActivity : Activity() {

    var recyclerView: RecyclerView by Delegates.notNull<RecyclerView>()
    var adapter: MemoListAdapter by Delegates.notNull<MemoListAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_list)

        initUI()
    }

    fun initUI() {
        val memoDao = MemoDao()
        adapter = MemoListAdapter(this, memoDao)
        memoDao.onDataChangeListener = object : MemoDao.OnDataChageListener {
            override fun onDataChanged() = adapter.notifyDataSetChanged()
        }


        recyclerView = findViewById(R.id.memolist_recyclerview) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val titleInputView = findViewById(R.id.memolist_input_title) as EditText
        val contentInputView = findViewById(R.id.memolist_input_content) as EditText
        findViewById(R.id.memolist_add_button).setOnClickListener {
            if (titleInputView.text.toString().length <= 0 || contentInputView.text.toString().length <= 0) {
                Toast.makeText(this.application, "Please input title/content", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            memoDao.add(Memo(title = titleInputView.text.toString(), content = contentInputView.text.toString()))
        }

        findViewById(R.id.memolist_sort_by_title_button).setOnClickListener {
            memoDao.sortByTitle();
        }

        findViewById(R.id.memolist_sort_by_created_time_button).setOnClickListener {
            memoDao.sortByCreatedTime();
        }

        findViewById(R.id.memolist_transform_title_button).setOnClickListener {
            memoDao.transformTitle {
                if (!it.startsWith("Title :")) {
                    return@transformTitle "Title : "+it
                }
                it
            }
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
            holder?.view?.setOnLongClickListener {
                memoDao.remove(position)
                true
            }
            holder?.titleView?.text = memoDao.list.get(position).title
            holder?.contentView?.text = memoDao.list.get(position).content
            holder?.createdTime?.text = memoDao.list.get(position).createdTime.toString()
        }
    }

    class MemoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView by lazy {
            view.findViewById(R.id.memo_list_item_title) as TextView
        }

        val contentView: TextView by lazy {
            view.findViewById(R.id.memo_list_item_content)    as TextView
        }

        val createdTime: TextView by lazy {
            view.findViewById(R.id.memo_list_item_created_time) as TextView
        }
    }
}