package com.linecorp.memo

import java.util.*

class MemoDao {
    val list = ArrayList<Memo>()

    fun add(memo: Memo) = list.add(memo)

    fun remove(position: Int) = list.remove(list[position])

    // TODO
    //    fun getRecentMemoList(createdTime: Long) : List<Memo> = list.filter {
    //
    //    }

    fun sortByTitle() = list.sortBy { it.title }

    fun sortByCreatedTime() = list.sortBy { it.createdTime }
}