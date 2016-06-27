package com.hyeyoung.memo

import java.util.*
import java.util.concurrent.locks.ReentrantLock
import kotlin.properties.Delegates

class MemoDao() {

    var onDataChangeListener: OnDataChageListener? = null

    var size: Int by Delegates.observable(Int.MIN_VALUE) {
        d, old, new ->
        onDataChangeListener?.onDataChanged()
    }

    val list = ArrayList<Memo>()
    val lock = ReentrantLock()


    fun add(memo: Memo) {
        lock(lock) {
            list.add(memo)
            size = list.size
        }
    }

    fun remove(position: Int) {
        lock(lock) {
            list.remove(list[position])
            size = list.size
        }
    }

    fun getRecentMemoList(createdTime: Long): List<Memo> = list.filter {
        it.createdTime > createdTime
    }

    fun sortByTitle() {
        lock(lock) {
            list.sortBy { it.title }
            size = list.size
        }
    }

    fun sortByCreatedTime() {
        lock(lock) {
            list.sortBy { it.createdTime }
            size = list.size
        }
    }

    fun transformTitle(transform: (String) -> String) {
        lock(lock) {
            for (item in list) {
                item.title = transform(item.title)
            }
            size = list.size
        }
    }

    fun <T> lock(lock: ReentrantLock, body: () -> T): T {
        lock.lock()
        try {
            return body()
        } finally {
            lock.unlock()
        }
    }

    interface OnDataChageListener {
        fun onDataChanged()
    }
}