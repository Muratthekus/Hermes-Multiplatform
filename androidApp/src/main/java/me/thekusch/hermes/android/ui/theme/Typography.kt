package me.thekusch.hermes.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.thekusch.hermes.R

val MulishFontFamily = FontFamily(
    Font(me.thekusch.hermes.android.R.font.mulish_semibold),
    Font(me.thekusch.hermes.android.R.font.mulish_bold, FontWeight.Bold),
    Font(me.thekusch.hermes.android.R.font.mulish_semibold,FontWeight.SemiBold)
)

val HermesTypography = Typography(
    h1 = TextStyle(
        fontSize = 32.sp,
        fontFamily = MulishFontFamily,
        fontWeight = FontWeight.Bold,
        //color = Color(0xFF0F1828),
    ),
    h2 = TextStyle(
        fontSize = 24.sp,
        fontFamily = MulishFontFamily,
        fontWeight = FontWeight.Bold,
        //color = Color(0xFF0F1828),
    ),
    subtitle1 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 30.sp,
        fontFamily = MulishFontFamily,
        fontWeight = FontWeight.SemiBold,
        //color = Color(0xFF0F1828),
    ),
    subtitle2 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 28.sp,
        fontFamily = MulishFontFamily,
        fontWeight = FontWeight.SemiBold,
        //color = Color(0xFF0F1828),
    ),
    body1 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontFamily = MulishFontFamily,
        fontWeight = FontWeight.SemiBold,
       // color = Color(0xFF0F1828),
    ),
    body2 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontFamily = MulishFontFamily,
        fontWeight = FontWeight.Normal,
        //color = Color(0xFF0F1828),
    ),

)