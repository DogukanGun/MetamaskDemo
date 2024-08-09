package com.dag.myapplication.composebase.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.dag.myapplication.R

@Composable
fun CustomDropdown(
    items:List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.White, CircleShape)
            .clickable { expanded = true },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Payment Method",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
            properties = PopupProperties()
        ) {
            items.forEachIndexed { index, title ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    Text(text = title)
                }
            }
        }
        Icon(
            painter = if (!expanded)
                painterResource(id = R.drawable.arrow_downward)
            else painterResource(id = R.drawable.arrow_upward),
            contentDescription = "arrow",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(16.dp),
            tint = Color.White
        )
    }
}