package com.giocode.example.calculator

import java.util.*
import kotlin.properties.Delegates

class History {
    var list: LinkedList<Result> by Delegates.observable(LinkedList<Result>()) {
        d, old, new ->
        onDataChangeListener?.onDataChanged()
    }
    var onDataChangeListener: OnDataChangeListener? = null

    fun add(result: Result) {
        if (!list.contains(result)) {
            list.add(0, result)
        } else {
            list.remove(result)
            list.add(0, result)
        }
        list = list
    }

    fun delete(position: Int) {
        list.removeAt(position)
        list = list
    }

    interface OnDataChangeListener {
        fun onDataChanged()
    }
}