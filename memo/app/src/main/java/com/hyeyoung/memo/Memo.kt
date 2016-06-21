package com.hyeyoung.memo

data class Memo(val createdTime: Long? = System.currentTimeMillis(), val title: String, val content: String)