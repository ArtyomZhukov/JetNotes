package com.zhukovartemvl.jetnotes.note_editor.commands


interface Command {
    val isReversible: Boolean
    fun execute()
    fun unExecute()
}
