package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.ui.common.*
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.*
import me.onebone.toolbar.*


@FlowPreview
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
fun SubjectsScreen(
    subjectsViewModel: SubjectsViewModel = hiltViewModel(),
    onMenuClicked : () -> Unit
) {

    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = subjectsViewModel.isUpdating.value
    )

    val subjects by subjectsViewModel.subjects.collectAsState()
    val visibleSubjects by subjectsViewModel.visibleSubjects.collectAsState()
    val currentSemester by subjectsViewModel.currentSemester


    when (subjects) {
        is DataState.Success<*>, is DataState.Loading -> {
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.secondary)
                        .statusBarsHeight()
                        .zIndex(2f)
                )
                CollapsingToolbarScaffold(
                    modifier = Modifier
                        .zIndex(1f)
                        .bsuBackgroundPattern(
                            color = MaterialTheme.colors.primary.copy(alpha = .05f),
                            clip = true
                        ),
                    state = scaffoldState,
                    enabled = refreshState.indicatorOffset == 0f,
                    scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
                    toolbar = {
                        Toolbar(
                            viewModel = subjectsViewModel,
                            toolbarState = scaffoldState.toolbarState,
                            onMenuClicked = onMenuClicked
                        )
                    })
                {
                    if (subjects is DataState.Success) {
                        Body(
                            initialSemester = currentSemester,
                            visibleSubjects = visibleSubjects.valueOrNull().orEmpty(),
                            subjects = subjects.valueOrNull()!!,
                            updater = subjectsViewModel,
                            refreshState = refreshState,
                        )
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()

                        ) {
                            BsuProgressBar(
                                Modifier.align(Alignment.Center),
                                size = 100.dp,
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }
        }
        is DataState.Empty, is DataState.Error -> Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            ErrorScreen(
                toolbarText = stringResource(id = R.string.subjects),
                title = stringResource(
                    id = if (subjects is DataState.Empty)
                        R.string.empty else R.string.something_gone_wrong
                ),
                error = stringResource(
                    id = if (subjects is DataState.Empty)
                        R.string.subjects_empty else (subjects as DataState.Error).message
                ),
                updater = subjectsViewModel,
                onMenuClicked = onMenuClicked
            )
        }
    }
}
@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@FlowPreview
@Composable
private fun Toolbar(
    viewModel : SubjectsViewModel,
    toolbarState : CollapsingToolbarState,
    onMenuClicked: () -> Unit) {


    CollapsingToolbar(
        Modifier
            .background(MaterialTheme.colors.secondary),
        collapsingToolbarState = toolbarState
    ) {

        var filtersVisible by rememberSaveable {
            mutableStateOf(false)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                BurgerMenuButton(onClick = onMenuClicked)
                DefaultTextInput(value = viewModel.searchText.value,
                    onValueChange = viewModel::search,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth()
                        .padding(end = 10.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    ),
                    leadingIcon = {
                        if (viewModel.searchText.value.isEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                modifier = Modifier
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .clickable {
                                        viewModel.search("")
                                    }
                            )
                        }
                    },
                    trailingIcon = {

                        Icon(
                            painter = painterResource(
                                if (filtersVisible) R.drawable.ic_filter_list_off
                                else R.drawable.ic_filter_list
                            ),
                            contentDescription = "Search",
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { filtersVisible = !filtersVisible }
                        )
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.subjects))
                    }
                )
            }
            AnimatedVisibility(visible = filtersVisible) {

                Row(
                    modifier = Modifier.animateEnterExit(
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
                ) {
                    Row(
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                            .clip(CircleShape)
                            .clickable {
                                viewModel.setFilter(
                                    withCredit = viewModel.withCredit.value.not(),
                                    withExam = viewModel.withExam.value
                                )

                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = viewModel.withCredit.value,
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colors.primary
                            ),
                            onCheckedChange = {
                                viewModel.setFilter(it,viewModel.withExam.value)
                            })
                        Text(text = stringResource(id = R.string.with_credit))
                    }
                    Row(
                        Modifier
                            .weight(1f)
                            .padding(horizontal = 5.dp)
                            .clip(CircleShape)
                            .clickable {
                                viewModel.setFilter(
                                    withCredit = viewModel.withCredit.value,
                                    withExam = viewModel.withExam.value.not()
                                )
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Checkbox(
                            checked = viewModel.withExam.value,
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colors.primary
                            ),
                            onCheckedChange = {
                                viewModel.setFilter(viewModel.withCredit.value,it)
                            })
                        Text(text = stringResource(id = R.string.with_exam))
                    }
                }
            }
        }
    }
}

@FlowPreview
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@Composable
private fun Body(
    initialSemester : Int,
    visibleSubjects: List<List<Subject>>,
    subjects : List<List<Subject>>,
    updater: Updatable,
    refreshState: SwipeRefreshState,
) {

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = initialSemester) {
        if (state.pageCount > initialSemester)
            state.scrollToPage(initialSemester)
    }


//    when (val subjectValue = subjects) {
//        is DataState.Success ->
    Scaffold(
        topBar = {
            ScrollableTabRow(
                selectedTabIndex = state.currentPage,
                backgroundColor = MaterialTheme.colors.secondary,
                edgePadding = 0.dp,
                indicator = { tabs ->
                    Box {
                        Spacer(
                            modifier =
                            Modifier
                                .let {
                                    if (state.pageCount == tabs.size) {
                                        it.pagerTabIndicatorOffset(
                                            pagerState = state,
                                            tabs
                                        )
                                    } else it
                                }
                                .height(2.dp)
                                .zIndex(Float.MAX_VALUE)
                                .align(Alignment.BottomCenter)
                                .background(MaterialTheme.colors.primary)
                        )
                    }
                }
            ) {
                for (i in subjects.indices) {
                    Tab(
                        selected = state.currentPage == i,
                        onClick = {
                            scope.launch {
                                state.scrollToPage(i)
                            }
                        }) {
                        Text(
                            text = stringResource(id = R.string.semester, i + 1),
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }
        }
    ) {

        val openedSubjects = remember {
            mutableStateListOf<Subject>()
        }
        SwipeRefresh(
            modifier = Modifier
                .fillMaxSize()
                .bsuBackgroundPattern(
                    MaterialTheme.colors.primary.copy(alpha = .05f),
                    clip = true
                ),
            indicator = { state, trigger ->
                BsuProgressBarSwipeRefreshIndicator(state = state, trigger = trigger)
            },
            state = refreshState,
            onRefresh = updater::update,
        ) {
            HorizontalPager(
                count = visibleSubjects.size,
                state = state,
                modifier = Modifier
                    .fillMaxSize()


            ) { page ->

                if (visibleSubjects[page].isNotEmpty()) {
                    Page(
                        modifier = Modifier.graphicsLayer {
                            translationY = refreshState.indicatorOffset
                        },

                        subjects = subjects[page],
                        visibleSubjects = visibleSubjects[page],
                        opened = openedSubjects,
                    )
                } else {
                    ErrorWidget(
                        title = stringResource(id = R.string.empty),
                        error = stringResource(id = R.string.subjects_not_found_for_day)
                    )
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun Page(
    subjects: List<Subject>,
    visibleSubjects : List<Subject>,
    opened : SnapshotStateList<Subject>,
    modifier: Modifier = Modifier
) {
    val mutableSubjects = remember {
        mutableStateListOf(*subjects.toTypedArray())
    }

    val subjectsInFirstColumn = remember {
        mutableStateOf(mutableSubjects.size / 2)
    }

    var shrinkAnimationEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    val lastElemHeight = listOf(
        remember { mutableStateOf(0) },
        remember { mutableStateOf(0) }
    )


    val columnHeight = listOf(
        remember { mutableStateOf(0) },
        remember { mutableStateOf(0) }
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(subjects) {
        if (mutableSubjects.toList() != subjects) {
            mutableSubjects.clear()
            mutableSubjects.addAll(subjects)
            subjectsInFirstColumn.value = mutableSubjects.size / 2
        }
    }

    LaunchedEffect(visibleSubjects) {
        delay(100)
        shrinkAnimationEnabled = true
        suspendRepeat {
            shrink(
                columnHeight[0].value,
                columnHeight[1].value,
                lastElemHeight[0].value,
                lastElemHeight[1].value,
                mutableSubjects,
                subjectsInFirstColumn
            )
        }
        delay(100)
        shrinkAnimationEnabled = false
    }

    LaunchedEffect(Unit) {
        delay(100)
        suspendRepeat {
            shrink(
                columnHeight[0].value,
                columnHeight[1].value,
                lastElemHeight[0].value,
                lastElemHeight[1].value,
                mutableSubjects,
                subjectsInFirstColumn
            )
        }
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .navigationBarsWithImePadding()
            .padding(bottom = 10.dp)
    ) {
        listOf(
            mutableSubjects.subList(0, subjectsInFirstColumn.value),
            mutableSubjects.subList(subjectsInFirstColumn.value, mutableSubjects.size)
        ).forEachIndexed { columnIndex, list ->
            Column(
                Modifier
                    .weight(1f)
//                    .animateContentSize(
//                        finishedListener = { _, _ ->
//                            shrinkAnimationEnabled = false
//                        }
//                    )
                    .onSizeChanged {
                        columnHeight[columnIndex].value = it.height
                    }
            ) {
                list.forEachIndexed { idx, subj ->
                    key(subj.name) {

                        var visible by rememberSaveable {
                            mutableStateOf(false)
                        }

                        LaunchedEffect(Unit) {
                            visible = true
                        }

                        AnimatedVisibility(
                            modifier = Modifier.padding(7.dp),
                            visible = visible && subj in visibleSubjects,
                            enter = if (shrinkAnimationEnabled) slideInHorizontally {
                                if (columnIndex == 0) it else -it
                            }
//                            else slideInHorizontally { if (columnIndex == 0) -it else it },
                            else EnterTransition.None,
//                            exit = slideOutHorizontally {
//                                if (columnIndex == 0) -it else it
//                            }
                            exit = ExitTransition.None

                        ) {

                            SubjectWidget(
                                subject = subj,
                                isOpened = subj in opened,
                                modifier = Modifier
                                    .let {

                                        if (idx == list.lastIndex) {
                                            it.onSizeChanged {
                                                lastElemHeight[columnIndex].value =
                                                    it.height
                                            }
                                        } else it
                                    }

                            ) {
                                scope.launch {
                                    if (!opened.remove(subj)) {
                                        opened.add(subj)
                                    }
                                    delay(300)
                                    shrinkAnimationEnabled = true
                                    suspendRepeat {
                                        shrink(
                                            columnHeight[0].value,
                                            columnHeight[1].value,
                                            lastElemHeight[0].value,
                                            lastElemHeight[1].value,
                                            mutableSubjects,
                                            subjectsInFirstColumn
                                        )
                                    }
                                    delay(200)
                                    shrinkAnimationEnabled = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private suspend fun suspendRepeat(delay:Long = 100, max:Int = 5, shrink : () ->Boolean){
    var cur = 0
    while (shrink() && cur<max){
        delay(delay)
        cur++
    }
}

private fun shrink(
    firstColumnHeight : Int,
    secondColumnHeight : Int,
    lastElemInFirstColumnHeight : Int,
    lastElemInSecondColumnHeight : Int,
    subjects : SnapshotStateList<Subject>,
    subjectsInFirstColumn : MutableState<Int>
) : Boolean {
    return when {
        (firstColumnHeight - secondColumnHeight).let {
            it > 0 && lastElemInFirstColumnHeight*1.2 < it &&
                    subjects.size > subjectsInFirstColumn.value - 1 &&
                    subjectsInFirstColumn.value >0
        } -> {
            subjects.add(subjects[subjectsInFirstColumn.value - 1])
            subjects.removeAt(subjectsInFirstColumn.value - 1)
            subjectsInFirstColumn.value--
            true
        }

        (secondColumnHeight - firstColumnHeight).let {
            it > 0 && lastElemInSecondColumnHeight*1.2 < it &&
                    subjects.size > subjectsInFirstColumn.value &&
                    subjectsInFirstColumn.value >= 0
        } -> {
            subjects.add(
                subjectsInFirstColumn.value,
                subjects.last()
            )
            subjects.removeLast()
            subjectsInFirstColumn.value++
            true
        }
        else -> false
    }
}