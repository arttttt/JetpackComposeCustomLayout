package com.androidinsights.jetpackcomposecustomlayout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density

@Composable
fun EqualHeightColumn(
    modifier: Modifier = Modifier,
    content: @Composable EqualHeightColumnScope.() -> Unit
) {
    Layout(
        modifier = modifier,
        content = { EqualHeightColumnScopeInstance.content() }
    ) { measurables: List<Measurable>, constraints: Constraints ->

        val maxHeight = measurables.maxOf { measurable ->
            measurable.minIntrinsicHeight(constraints.maxHeight)
        }

        val updatedConstraints = constraints.copy(
            minHeight = maxHeight,
            maxHeight = maxHeight,
        )

        val placeables = measurables.map {
            if (it.ignoreEqualHeight) {
                it.measure(constraints)
            } else {
                it.measure(updatedConstraints)
            }
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var y = 0
            placeables.forEach {
                it.placeRelative(0, y)

                y += it.height
            }
        }
    }
}

interface EqualHeightColumnScope {

    fun Modifier.ignoreEqualHeight(): Modifier
}

object EqualHeightColumnScopeInstance : EqualHeightColumnScope {

    override fun Modifier.ignoreEqualHeight(): Modifier {
        return this.then(
            EqualHeightColumnChildData(
                ignoreEqualHeight = true,
            )
        )
    }
}

data class EqualHeightColumnChildData(
    val ignoreEqualHeight: Boolean
) : ParentDataModifier {

    override fun Density.modifyParentData(parentData: Any?): Any {
        return this@EqualHeightColumnChildData
    }
}

val Measurable.ignoreEqualHeight: Boolean
    get() = (this.parentData as? EqualHeightColumnChildData)
        ?.ignoreEqualHeight ?: false