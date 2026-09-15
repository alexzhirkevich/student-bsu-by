@file:OptIn(ExperimentalForeignApi::class)

package github.alexzhirkevich.studentbsuby.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.autoreleasepool
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import platform.Foundation.*
import platform.UIKit.*
import platform.Vision.*

class CaptchaRecognizerImpl : CaptchaRecognizer {

    override suspend fun recognize(image: ImageBitmap): String =
        withContext(Dispatchers.Default) {
            kotlin.runCatching {
                autoreleasepool {
                    recognize(
                        Image.makeFromBitmap(image.asSkiaBitmap())
                            .encodeToData(EncodedImageFormat.PNG)!!
                            .bytes
                    )
                }
            }.getOrDefault("")
        }

    private fun recognize(png: ByteArray): String {
        val data = png.usePinned {
            NSData.create(bytes = it.addressOf(0), length = png.size.toULong())
        }
        val cgImage = UIImage.imageWithData(data)!!.CGImage!!

        val request = VNRecognizeTextRequest().apply {
            recognitionLevel = VNRequestTextRecognitionLevelAccurate
            usesLanguageCorrection = false
        }
        VNImageRequestHandler(cGImage = cgImage, options = emptyMap<Any?, Any>())
            .performRequests(listOf(request), null)

        return (request.results?.firstOrNull() as? VNRecognizedTextObservation)
            ?.topCandidates(1u)
            ?.firstOrNull()
            .let { it as? VNRecognizedText }
            ?.string
            .orEmpty()
    }
}

actual fun createCaptchaRecognizer(): CaptchaRecognizer = CaptchaRecognizerImpl()
