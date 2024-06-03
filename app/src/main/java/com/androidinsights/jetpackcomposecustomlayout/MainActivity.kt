package com.androidinsights.jetpackcomposecustomlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.androidinsights.jetpackcomposecustomlayout.ui.theme.JetpackComposeCustomLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeCustomLayoutTheme {
                EqualHeightColumn {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .background(Color.Red),
                    )

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .ignoreEqualHeight()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(Color.Green),
                    )

                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .ignoreEqualHeight()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .background(Color.Blue),
                    )
                }
            }
        }
    }
}