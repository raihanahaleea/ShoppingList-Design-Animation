package com.example.materialtheme.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Material Design Shape System
 * Mendefinisikan berbagai bentuk/radius yang digunakan dalam aplikasi
 * sesuai dengan Material Design guidelines
 */
val Shapes = Shapes(
    // Extra Small - untuk komponen kecil seperti chips, badges
    extraSmall = RoundedCornerShape(4.dp),

    // Small - untuk buttons, text fields
    small = RoundedCornerShape(8.dp),

    // Medium - untuk cards, dialog
    medium = RoundedCornerShape(12.dp),

    // Large - untuk bottom sheets, large components
    large = RoundedCornerShape(16.dp),

    // Extra Large - untuk full screen components
    extraLarge = RoundedCornerShape(28.dp)
)

/**
 * Custom shapes untuk kebutuhan spesifik
 */
object CustomShapes {
    // Rounded corners hanya di atas
    val topRounded = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    // Rounded corners hanya di bawah
    val bottomRounded = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 16.dp,
        bottomEnd = 16.dp
    )

    // Rounded corners hanya di kiri
    val leftRounded = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 0.dp,
        bottomStart = 16.dp,
        bottomEnd = 0.dp
    )

    // Rounded corners hanya di kanan
    val rightRounded = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 16.dp,
        bottomStart = 0.dp,
        bottomEnd = 16.dp
    )

    // Circle shape untuk FAB, avatar, dll
    val circle = RoundedCornerShape(50)

    // Slight rounded untuk subtle corners
    val slightRounded = RoundedCornerShape(4.dp)

    // Extra rounded untuk prominent components
    val extraRounded = RoundedCornerShape(24.dp)

    // Card shapes dengan variasi
    val cardSmall = RoundedCornerShape(8.dp)
    val cardMedium = RoundedCornerShape(12.dp)
    val cardLarge = RoundedCornerShape(16.dp)

    // Button shapes
    val buttonSmall = RoundedCornerShape(20.dp)
    val buttonMedium = RoundedCornerShape(24.dp)
    val buttonLarge = RoundedCornerShape(28.dp)

    // Sheet shapes
    val bottomSheet = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    // Dialog shapes
    val dialog = RoundedCornerShape(16.dp)

    // Chip shapes
    val chip = RoundedCornerShape(16.dp)

    // Search bar shape
    val searchBar = RoundedCornerShape(28.dp)
}