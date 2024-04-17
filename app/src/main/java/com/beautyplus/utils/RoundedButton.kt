package com.beautyplus.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beautyplus.ui.theme.appColor

@Composable
fun RoundedButton(text: String, isEnabled : Boolean = true, onClick: () -> Unit, textColor : Color = Color.White) {
    GradientButton(
        onClick = onClick,
        modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
        textColor = textColor,
        isEnabled = isEnabled,
        gradient = Brush.horizontalGradient(listOf(appColor, appColor)),
        text = text
    )
}

@Composable
fun GradientButton(
    text: String,
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    textColor: Color,
    isEnabled: Boolean
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = textColor),
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(30.dp),
        enabled = isEnabled,
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier.background(gradient).then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(10.dp))
        }
    }
}