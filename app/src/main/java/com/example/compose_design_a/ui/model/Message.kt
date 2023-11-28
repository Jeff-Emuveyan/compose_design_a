package com.example.compose_design_a.ui.model

import androidx.compose.ui.graphics.Color

data class Message(
    val name: String,
    val image: Int,
    val background: Color,
    val message: String,
    val time: Int,
    val numberOfUnreadMessages: Int
) {

    fun getFriend(): Friend {
        return Friend(name, image, background)
    }
}