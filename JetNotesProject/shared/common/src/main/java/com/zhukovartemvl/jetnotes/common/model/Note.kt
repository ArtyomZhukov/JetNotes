package com.zhukovartemvl.jetnotes.common.model


data class Note(
    var id: Int = -1,
    var title: String = "",
    var content: String = "",
    var createdTime: Long = 0,
    var changedTime: Long = 0
)

