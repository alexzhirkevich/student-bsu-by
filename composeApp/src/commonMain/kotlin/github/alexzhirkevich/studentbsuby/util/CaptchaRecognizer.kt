package github.alexzhirkevich.studentbsuby.util

import androidx.compose.ui.graphics.ImageBitmap

interface CaptchaRecognizer {
    suspend fun recognize(image: ImageBitmap) : String
}

expect fun createCaptchaRecognizer(): CaptchaRecognizer
