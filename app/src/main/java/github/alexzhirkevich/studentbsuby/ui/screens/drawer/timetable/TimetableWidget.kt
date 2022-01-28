package github.alexzhirkevich.studentbsuby.ui.screens.drawer.timetable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import github.alexzhirkevich.studentbsuby.data.models.Lesson

@ExperimentalMaterialApi
@Preview()
@Composable
fun prev(modifier: Modifier = Modifier) {
    TimetableWidget(
        modifier = Modifier.fillMaxSize(),
        list = listOf(
            Lesson(
                0,
                "",
                0,
                1,
                "Методы оптимизации",
                "607, ул. Ленинградская 8",
                "лабораторные занятия",
                "Преподовате Л.Ь.",
                "8.15",
                "9.35"
            ) to LessonState.PASSED,
            Lesson(
                1,
                "",
                0,
                2,
                "Арифметические и алгебраические основы криптографии",
                "604",
                "лекции",
                "Преподовате Л.Ь.",
                "9.45",
                "11.05"
            ) to LessonState.PASSED,
            Lesson(
                2,
                "",
                0,
                3,
                "Криптографические методы",
                "604",
                "лекции",
                "Преподовате Л.Ь.",
                "11.15",
                "12.35"
            ) to LessonState.RUNNING,
            Lesson(
                3,
                "",
                0,
                4,
                "Криптографические методы",
                "604",
                "лекции",
                "Преподовате Л.Ь.",
                "13.00",
                "14.20"
            ) to LessonState.INCOMING,
        )
    )
}

private const val HorizontalPadding = 10
@ExperimentalMaterialApi
@Composable
fun TimetableWidget(
    list: List<Pair<Lesson,LessonState>>,
    backgroundColor : Color = MaterialTheme.colors.background,
    modifier: Modifier = Modifier) {

    val lineColor = MaterialTheme.colors.primary.copy(alpha = .5f)
    val density = LocalDensity.current.density

    var timelineStart by rememberSaveable {
        mutableStateOf(0f)
    }
    var timelineEnd by rememberSaveable {
        mutableStateOf(0f)
    }

    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = modifier
            .let {
                if (list.size>1){
                    it.drawBehind {
                        drawLine(
                            color = lineColor,
                            strokeWidth = 1 * density,
                            start = Offset(
                                x = density * (LessonTimeLineOffsetX + HorizontalPadding),
                                y = timelineStart
                            ),
                            end = Offset(
                                x = density * (LessonTimeLineOffsetX + HorizontalPadding),
                                y = timelineEnd
                            ),
                        )
                    }
                } else it
            }
            .navigationBarsWithImePadding()
    ) {

        items(list.size){ idx ->
            LessonWidget(
                lesson = list[idx].first,
                state = list[idx].second,
                backgroundColor = backgroundColor,
                modifier = Modifier.padding(
                    horizontal = HorizontalPadding.dp,
                    vertical = 15.dp
                ).let {
                    when(idx){
                        0 -> it.onSizeChanged {
                            timelineStart = it.height.toFloat() /2
                        }
                        list.size-1 -> it.onGloballyPositioned {
                            timelineEnd = it.positionInParent().y + it.size.height.toFloat()/2
                        }
                        else -> it
                    }
                }
            )
        }
    }
}