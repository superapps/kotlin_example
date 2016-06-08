package com.hyeyoung.memo

data class Memo(val createdTime: Long? = System.currentTimeMillis(), val title: String? = "Title", val content: String? = "Content")