package com.androidinsights.jetpackcomposecustomlayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints

@Composable
fun BasicCustomLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables: List<Measurable>, constraints: Constraints ->

        val placeables = measurables.map { it.measure(constraints) }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var y = 0
            placeables.forEach {
                it.placeRelative(0, y)

                y += it.height
            }
        }
    }
}