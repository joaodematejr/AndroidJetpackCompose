package com.demate.noteapp.model

import java.util.Date
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),  //9888-10848-222222
    val title: String,
    val description: String,
    //val entryDate: LocalDateTime = LocalDateTime.now()
    val entryDate: String = Date().toString()

)
