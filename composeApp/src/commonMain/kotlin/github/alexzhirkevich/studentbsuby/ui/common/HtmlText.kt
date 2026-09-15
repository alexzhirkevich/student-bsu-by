package github.alexzhirkevich.studentbsuby.ui.common

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.fleeksoft.ksoup.Ksoup
import com.fleeksoft.ksoup.nodes.Element
import com.fleeksoft.ksoup.nodes.Node
import com.fleeksoft.ksoup.nodes.TextNode
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * Renders a string containing simple html markup (<a href>, <b>, <i>, <br>).
 * Links are opened with [androidx.compose.ui.platform.LocalUriHandler].
 */
@Composable
fun HtmlText(
    modifier: Modifier = Modifier,
    textId: StringResource,
    urlSpanStyle: SpanStyle = SpanStyle(
        color = MaterialTheme.colors.secondary,
        textDecoration = TextDecoration.Underline
    ),
    style: TextStyle = LocalTextStyle.current
) {
    HtmlText(
        modifier = modifier,
        text = stringResource(textId),
        urlSpanStyle = urlSpanStyle,
        style = style
    )
}

@Composable
fun HtmlText(
    modifier: Modifier = Modifier,
    text: String,
    urlSpanStyle: SpanStyle = SpanStyle(
        color = MaterialTheme.colors.secondary,
        textDecoration = TextDecoration.Underline
    ),
    style: TextStyle = LocalTextStyle.current
) {
    val annotatedString = remember(text, urlSpanStyle) {
        text.htmlToAnnotatedString(urlSpanStyle)
    }
    Text(
        text = annotatedString,
        modifier = modifier,
        style = style
    )
}

fun CharSequence.toAnnotatedString(
    urlSpanStyle: SpanStyle = SpanStyle(
        color = Color.Blue,
        textDecoration = TextDecoration.Underline
    )
): AnnotatedString = toString().htmlToAnnotatedString(urlSpanStyle)

fun String.htmlToAnnotatedString(urlSpanStyle: SpanStyle): AnnotatedString {
    val body = Ksoup.parse(this).body()
    return buildAnnotatedString {
        appendChildren(body, urlSpanStyle)
    }
}

private fun AnnotatedString.Builder.appendChildren(node: Node, urlSpanStyle: SpanStyle) {
    node.childNodes().forEach { child ->
        when (child) {
            is TextNode -> append(child.getWholeText())
            is Element -> when (child.tagName().lowercase()) {
                "a" -> withLink(
                    LinkAnnotation.Url(
                        url = child.attr("href"),
                        styles = TextLinkStyles(style = urlSpanStyle)
                    )
                ) {
                    appendChildren(child, urlSpanStyle)
                }
                "b", "strong" -> withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    appendChildren(child, urlSpanStyle)
                }
                "i", "em" -> withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                    appendChildren(child, urlSpanStyle)
                }
                "br" -> append('\n')
                else -> appendChildren(child, urlSpanStyle)
            }
        }
    }
}
