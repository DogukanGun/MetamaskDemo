package com.dag.myapplication.composebase.switchbutton

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BuySellToggleButton() {
    var selectedOption by remember { mutableStateOf("BUY") }
    val options = listOf("BUY", "SELL")

    Row {
        options.forEach { option ->
            RadioButton(
                selected = selectedOption == option,
                onClick = { selectedOption = option },
                colors = RadioButtonDefaults.colors(
                    selectedColor = if (selectedOption == option) Color.Green else Color.Transparent,
                    unselectedColor = Color.Transparent
                )
            )
            Text(
                text = option,
                modifier = Modifier.clickable { selectedOption = option },
                color = if (selectedOption == option) Color.White else Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}