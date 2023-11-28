package com.example.compose_design_a.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.compose_design_a.R
import com.example.compose_design_a.ui.model.Friend

class FriendPreviewParameter: PreviewParameterProvider<Friend> {

    override val values: Sequence<Friend> = sequenceOf(
        Friend("Sandra", R.drawable.girl_a, Color.Yellow)
    )
}