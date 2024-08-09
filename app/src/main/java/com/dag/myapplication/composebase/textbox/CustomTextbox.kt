package com.dag.myapplication.composebase.textbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dag.myapplication.R
import com.dag.myapplication.composebase.BlockPreview
import com.dag.nexarbmobile.composebase.textbox.TextboxTypes

@Composable
fun BlockTextbox(
    modifier: Modifier = Modifier,
    onValueChange:(String)->Unit,
    placeholder: Int? = null,
    textboxTypes: TextboxTypes = TextboxTypes.Text
){
    var textfieldValue by remember { mutableStateOf("") }

    TextField(
        value = if (textboxTypes == TextboxTypes.Password) textfieldValue.map { '*' }.toString()
        else textfieldValue,
        onValueChange = { text->
            textfieldValue = text
            onValueChange(text)
        },
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = Color.Transparent,
            disabledTextColor = Color.Gray,
            focusedIndicatorColor = MaterialTheme.colors.primary,
            unfocusedIndicatorColor = Color.Gray
        ),
        placeholder = {
            placeholder?.let {
                Text(
                    text = stringResource(id = it),
                    modifier = Modifier.fillMaxWidth(fraction = 0.8f),
                    style = MaterialTheme.typography.body2.copy(color = Color.Gray.copy(alpha = 0.8f))
                )
            }
        }
    )
}

@Composable
@Preview
fun NexarbTextboxPreview(){
    BlockPreview {
        BlockTextbox(
            placeholder = R.string.app_name,
            onValueChange = {}
        )
    }
}

@Composable
@Preview
fun NexarbTextboxPasswordPreview(){
    BlockPreview {
        BlockTextbox(
            textboxTypes = TextboxTypes.Password,
            onValueChange = {}
        )
    }
}