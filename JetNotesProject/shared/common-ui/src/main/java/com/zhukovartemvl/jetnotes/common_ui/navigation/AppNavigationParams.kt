package com.zhukovartemvl.jetnotes.common_ui.navigation


object AppNavigationParams {

    object Screen {
        const val Main = "main"
        const val About = "about"
        fun NoteEditor(noteId: Int = -1) = Path.NoteEditor + noteId
    }

    object Destination {
        const val NoteEditor = Path.NoteEditor + "{" + Argument.NoteId + "}"
    }

    object Argument {
        const val NoteId = "noteId"
    }

    private object Path {
        const val NoteEditor = "noteEditor/"
    }

}

