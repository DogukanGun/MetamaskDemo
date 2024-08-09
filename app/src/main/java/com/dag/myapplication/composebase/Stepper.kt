package com.dag.myapplication.composebase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StepProgressIndicator(step: Int,totalSteps:Int) {
    Column {
        Text("STEP $step", color = Color.White, fontSize = 20.sp) // Adjust text color and size
        LinearProgressIndicator(
            progress = step.toFloat() / totalSteps, // Calculate progress based on the current step
            backgroundColor = Color.Gray, // The color of the unfilled portion of the progress bar
            color = Color.Green, // The color of the filled portion of the progress bar
            modifier = Modifier.fillMaxWidth().height(12.dp) // Adjust height and fill width as needed
        )
    }
}