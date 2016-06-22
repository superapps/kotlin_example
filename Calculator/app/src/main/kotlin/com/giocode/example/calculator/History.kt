package com.giocode.example.calculator

import java.util.*
import kotlin.properties.Delegates

class History {
    var list: ArrayList<Result> by Delegates.observable(ArrayList<Result>()) {
        d, old, new ->
        onDataChangeListener?.onDataChanged()
    }
    var onDataChangeListener: OnDataChangeListener? = null

    fun add(result: Result) {
        if (!list.contains(result)) {
            list.add(result)
        } else {
            list.remove(result)
            list.add(result)
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
