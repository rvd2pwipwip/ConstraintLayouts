package com.hdesrosiers.constraintlayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val constraints = ConstraintSet {
        val greenBox = createRefFor("greenbox")
        val redBox = createRefFor("redbox")

        constrain(greenBox) {
          start.linkTo(parent.start)
          top.linkTo(parent.top)
          end.linkTo(parent.end)
          bottom.linkTo(redBox.top)
          width = Dimension.percent(0.8f)
          height = Dimension.value(100.dp)
        }
        constrain(redBox) {
          start.linkTo(greenBox.start)
          top.linkTo(greenBox.bottom)
          end.linkTo(greenBox.end)
          bottom.linkTo(parent.bottom)
          width = Dimension.fillToConstraints
          height = Dimension.value(100.dp)
        }
        createVerticalChain(
          greenBox, redBox,
          chainStyle = ChainStyle.Packed
        )
      }
      ConstraintLayout(
        constraintSet = constraints,
        modifier = Modifier
          .fillMaxSize()
      ) {
        Box(
          modifier = Modifier
            .background(color = Color.Green)
            .layoutId("greenbox")
        )
        Box(
          modifier = Modifier
            .background(color = Color.Red)
            .layoutId("redbox")
        )
      }
    }
  }
}