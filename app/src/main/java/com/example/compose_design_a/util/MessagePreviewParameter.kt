package com.example.compose_design_a.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.compose_design_a.R
import com.example.compose_design_a.ui.model.Friend
import com.example.compose_design_a.ui.model.Message
import com.example.compose_design_a.ui.theme.CYAN

class MessagePreviewParameter: PreviewParameterProvider<Message> {
    override val values: Sequence<Message> = sequenceOf(
        Message(
            "Sandra",
            R.drawable.girl_a,
            CYAN,
            "Hey @Jeff.",
            4,
            2
        )
    )
}