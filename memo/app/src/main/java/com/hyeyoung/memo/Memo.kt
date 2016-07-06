package com.hyeyoung.memo

data class Memo(val createdTime: Long = System.currentTimeMillis(), var title: String = "Default title", var content: String = "Default content")