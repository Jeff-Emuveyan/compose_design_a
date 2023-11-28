package com.example.compose_design_a.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose_design_a.R
import com.example.compose_design_a.ui.model.Friend
import com.example.compose_design_a.ui.model.Message
import com.example.compose_design_a.ui.theme.ASH
import com.example.compose_design_a.ui.theme.ASH_1
import com.example.compose_design_a.ui.theme.CYAN
import com.example.compose_design_a.ui.theme.DARKER_ASH
import com.example.compose_design_a.ui.theme.DARK_ASH
import com.example.compose_design_a.ui.theme.Dark
import com.example.compose_design_a.ui.theme.Dark_Purple
import com.example.compose_design_a.ui.theme.Light_Purple
import com.example.compose_design_a.ui.theme.OFF_WHITE
import com.example.compose_design_a.ui.theme.Purple
import com.example.compose_design_a.ui.theme.SHINNY_GREEN
import com.example.compose_design_a.ui.theme.SKY_BLUE
import com.example.compose_design_a.util.FriendPreviewParameter
import com.example.compose_design_a.util.MessagePreviewParameter

/**
 * Figma design can be found here:
 * https://www.figma.com/file/7uw8RFHKOW9I2osbGKXuIq/Chat-App---UI-(Community)?type=design&node-id=0-1&mode=design&t=MzMeTrAnwLtryIep-0
 * **/

val friends = listOf(
    Friend("Isabelle", R.drawable.girl_a, Color.Yellow),
    Friend("Jeff", R.drawable.man_a, SKY_BLUE),
    Friend("Harper", R.drawable.girl_b, CYAN),
    Friend("Alexander", R.drawable.man_b, SKY_BLUE),
    Friend("Ethan", R.drawable.man_a, SKY_BLUE)
)

val messages = listOf(
    Message("Isabelle", R.drawable.girl_a, Color.Yellow, "Hey @Jeff!!!", 4, 2),
    Message("Harper", R.drawable.girl_b, CYAN, "Check your email", 2, 7),
    Message("Ethan", R.drawable.man_a, SKY_BLUE, "Want to hangout?", 6, 1),
    Message("Isabelle", R.drawable.girl_a, Color.Yellow, "Hey @Jeff", 4, 2),
    Message("Harper", R.drawable.girl_b, CYAN, "Check your email", 2, 7),
    Message("Ethan", R.drawable.man_a, SKY_BLUE, "Want to hangout?", 6, 1)
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun AppScreen() {

    Scaffold(
        topBar = {
            AppBar()
        },
        bottomBar = {
            // Because of the floating design of the bottom bar in the mock up, we are not going to use this
            // bottomBar composable. Instead we will create a bottom bar ourselves.
        },

    ) { innerPadding ->
        val brush = Brush.verticalGradient(listOf(Dark, Dark_Purple))

        ConstraintLayout {
            val (content, bottomBar) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(brush)
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                SearchBar()

                Spacer(modifier = Modifier.height(24.dp))

                ActiveIndicator()

                Spacer(modifier = Modifier.height(16.dp))

                FriendsList(friends)

                Spacer(modifier = Modifier.height(24.dp))

                Recents()

                Spacer(modifier = Modifier.height(16.dp))

                Messages(messages)
            }

            Box(modifier = Modifier
                    .constrainAs(bottomBar) {
                        bottom.linkTo(content.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }.padding(top = 24.dp, start = 24.dp, end = 24.dp)) {
                BottomBar()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Dark)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Messages", color = ASH_1, style = MaterialTheme.typography.titleLarge)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painterResource(id = R.drawable.filter ),
                contentDescription = "Favorite",
                tint = ASH_1
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text(
                text = "Search...",
                color = ASH_1,
                style = MaterialTheme.typography.labelMedium
            )
        },
        trailingIcon = {
            Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "")
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = DARK_ASH,
            focusedIndicatorColor = DARK_ASH,
            unfocusedIndicatorColor = DARK_ASH
        ),
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun ActiveIndicator() {
    val brush = Brush.horizontalGradient(listOf(Purple, Light_Purple))

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(brush)
            .padding(horizontal = 10.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            color = Color.White,
            text = "Currently Active",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Icon(
            painterResource(id = R.drawable.circle),
            contentDescription = "contentDescription",
            tint = SHINNY_GREEN,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Friend(
    @PreviewParameter(FriendPreviewParameter::class) friend: Friend,
    height: Dp = 114.dp,
    weight: Dp = 90.dp,
    showName: Boolean = true
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(weight, height)
                .clip(CircleShape)
                .background(friend.background)) {

            Image(
                painter = painterResource(id = friend.image),
                contentDescription = "",
                modifier = Modifier.size(weight, height)
            )
        }

        if (showName) {
            Text(friend.name, color = ASH,
                fontStyle = FontStyle(R.font.gilroy_regular),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun FriendsList(list: List<Friend>) {

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(list) {
            Friend(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Recents() {
    val brush = Brush.horizontalGradient(listOf(Purple, Light_Purple))

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(brush)
            .padding(horizontal = 10.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            color = Color.White,
            text = "Recents",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Image(
            painterResource(id = R.drawable.time_square),
            contentDescription = "contentDescription",
            modifier = Modifier.size(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Message(@PreviewParameter(MessagePreviewParameter::class) message: Message) {

    val brush = Brush.horizontalGradient(listOf(OFF_WHITE, DARK_ASH))

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .border(width = .8.dp, brush = brush, shape = RoundedCornerShape(24.dp))
                .clip(RoundedCornerShape(24.dp))
                .fillMaxWidth()
                .background(color = DARKER_ASH)
                .padding(8.dp)
        ) {

            Friend(friend = message.getFriend(), 80.dp, 80.dp, false)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Text(text = message.name, color = ASH, style = MaterialTheme.typography.displaySmall)
                    Text(
                        text = "${message.time} min",
                        color = Purple,
                        style = MaterialTheme.typography.displaySmall
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = message.message,
                        color = Color.White,
                        modifier = Modifier.padding(end = 8.dp),
                        fontStyle = FontStyle(R.font.gilroy_regular),
                        fontWeight = FontWeight.Medium
                    )

                    Box(
                        contentAlignment= Alignment.Center,
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(Purple)
                    ) {
                        Text(
                            text = "${message.numberOfUnreadMessages}",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun Messages(list: List<Message>) {

    LazyColumn(modifier = Modifier.height(400.dp)) {
        items(list) {
            Message(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBar() {
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().
            clip(CircleShape).
            background(DARK_ASH).
            padding(horizontal = 24.dp, vertical = 20.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_chat), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.ic_call), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.ic_camera), contentDescription = "")
        Image(painter = painterResource(id = R.drawable.ic_settings), contentDescription = "")
    }
}




