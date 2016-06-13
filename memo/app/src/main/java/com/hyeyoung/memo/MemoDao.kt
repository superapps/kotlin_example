package com.hyeyoung.memo

import java.util.*
import kotlin.properties.Delegates

class MemoDao() {

    var onDataChangeListener: OnDataChageListener? = null

    var size: Int by Delegates.observable(Int.MIN_VALUE) {
        d, old, new ->
        onDataChangeListener?.onDataChanged()
    }

    val list = ArrayList<Memo>()


    fun add(memo: Memo) {
        list.add(memo)
        size = list.size
    }

    fun remove(position: Int) {
        list.remove(list[position])
        size = list.size
    }

    // TODO
    //    fun getRecentMemoList(createdTime: Long) : List<Memo> = list.filter {
    //
    //    }

    fun sortByTitle() {
        list.sortBy { it.title }
        size = list.size
    }

    fun sortByCreatedTime() {
        list.sortBy { it.createdTime }
        size = list.size
    }

    interface OnDataChageListener {
        fun onDataChanged()
    }
}