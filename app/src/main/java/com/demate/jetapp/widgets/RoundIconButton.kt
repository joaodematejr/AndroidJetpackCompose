package com.demate.jetapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp


val IconbuttonSizeModifier = Modifier.size(40.dp)

@Composable
fun RoundIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Black.copy(alpha = 0.8f),
) {

    Card(
        modifier = Modifier
            .padding(all = 4.dp)
            .clickable { onClick.invoke() }
            .then(IconbuttonSizeModifier),
        shape = CircleShape,
    ) {
        Icon(
            painter = rememberVectorPainter(imageVector),
            contentDescription = "Plus or Minus",
            tint = tint
        )
    }

}
