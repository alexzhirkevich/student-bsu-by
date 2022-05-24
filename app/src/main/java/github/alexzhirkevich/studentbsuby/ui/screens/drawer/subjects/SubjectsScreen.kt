package github.alexzhirkevich.studentbsuby.ui.screens.drawer.subjects

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.*
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
import github.alexzhirkevich.studentbsuby.util.*
import kotlinx.coroutines.*
import me.onebone.toolbar.*
import kotlin.math.ceil
import kotlin.math.roundToInt



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

    val subjects by subjectsViewModel.subjects.collectAsState()

    when (subjects) {
        is DataState.Success<*>, is DataState.Loading -> {
           SuccessSubjectsScreen(
               subjectsViewModel = subjectsViewModel,
               subjects = subjects,
               onMenuClicked = onMenuClicked,
           )
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

@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi

@Composable
private fun SuccessSubjectsScreen(
    subjectsViewModel: SubjectsViewModel,
    subjects: DataState<List<List<Subject>>>,
    onMenuClicked: () -> Unit,
) {

    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val refreshState = rememberSwipeRefreshState(
        isRefreshing = subjectsViewModel.isUpdating.value
    )

    val visibleSubjects by subjectsViewModel.visibleSubjects.collectAsState()
    val currentSemester by subjectsViewModel.currentSemester
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
                    color = MaterialTheme.colors.primary.copy(alpha = .05f)
                ),
            state = scaffoldState,
            enabled = refreshState.indicatorOffset == 0f,
            scrollStrategy = ScrollStrategy.EnterAlwaysCollapsed,
            toolbarModifier = Modifier
                .background(MaterialTheme.colors.primary),
            toolbar = {
                Toolbar(
                    viewModel = subjectsViewModel,
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
                    searchText = subjectsViewModel.searchText.value,
                    onSemesterChanged = subjectsViewModel::setCurrentSemester
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

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi

@Composable
private fun Toolbar(
    viewModel: SubjectsViewModel,
    onMenuClicked: () -> Unit
) {

    var filtersVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(
            MaterialTheme.colors.secondary
        )
    ) {

        TopAppBar(
            elevation = 0.dp,
            backgroundColor = Color.Transparent
        ) {
            NavigationMenuButton(onClick = onMenuClicked)
            DefaultTextInput(value = viewModel.searchText.value,
                onValueChange = viewModel::search,
                singleLine = true,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp),
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
                modifier = Modifier
                    .animateEnterExit(
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
                            checkedColor = MaterialTheme.colors.primary,
                            checkmarkColor = MaterialTheme.colors.onPrimary
                        ),
                        onCheckedChange = {
                            viewModel.setFilter(it, viewModel.withExam.value)
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
                            checkedColor = MaterialTheme.colors.primary,
                            checkmarkColor = MaterialTheme.colors.onPrimary
                        ),
                        onCheckedChange = {
                            viewModel.setFilter(viewModel.withCredit.value, it)
                        })
                    Text(text = stringResource(id = R.string.with_exam))
                }
            }
        }
    }
}

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
    searchText : String,
    onSemesterChanged: (Int) -> Unit
) {

    if (searchText.isNotBlank())
        SearchSubjectsBody(
            visibleSubjects = visibleSubjects,
            searchText = searchText
        )
    else
        AllSemestersBody(
            initialSemester = initialSemester,
            visibleSubjects = visibleSubjects ,
            subjects = subjects,
            updater = updater,
            refreshState = refreshState,
            onSemesterChanged = onSemesterChanged
        )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun SearchSubjectsBody(
    visibleSubjects: List<List<Subject>>,
    searchText: String
){

    if (visibleSubjects.isNotEmpty()) {
        val openedSubject = remember {
            mutableStateListOf<Subject>()
        }

        LazyColumn(Modifier.fillMaxSize()) {
            visibleSubjects.forEachIndexed { idx, list ->
                if (list.isNotEmpty()) {
                    stickyHeader {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(5.dp)

                        ) {
                            Text(
                                text = stringResource(id = R.string.semester, idx + 1),
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(MaterialTheme.colors.background.copy(.9f))
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                                    .align(Alignment.TopCenter)
                            )
                        }
                    }

                    item {
                        Page(
                            subjects = list,
                            opened = openedSubject,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            item { 
                Spacer(modifier = Modifier.navigationBarsWithImePadding())
            }
        }
    } else {
        Box(Modifier.fillMaxSize()) {

            ErrorWidget(
                title = stringResource(id = R.string.empty),
                error = stringResource(id = R.string.subjects_not_found_search,searchText),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
private fun AllSemestersBody(
    initialSemester : Int,
    visibleSubjects: List<List<Subject>>,
    subjects : List<List<Subject>>,
    updater: Updatable,
    refreshState: SwipeRefreshState,
    onSemesterChanged : (Int) -> Unit
) {
    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (state.pageCount > initialSemester)
            state.scrollToPage(initialSemester)
    }

    LaunchedEffect(key1 = state.currentPage){
        onSemesterChanged(state.currentPage)
    }

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            ScrollableTabRow(
                selectedTabIndex = state.currentPage,
                backgroundColor = MaterialTheme.colors.secondary,
                edgePadding = 0.dp,
                modifier = Modifier.fillMaxWidth(),
                indicator = { tabs ->
                    Box {
                        Spacer(
                            modifier =
                            Modifier
                                .applyIf(state.pageCount == tabs.size) {
                                    it.pagerTabIndicatorOffset(
                                        pagerState = state,
                                        tabs
                                    )
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
                            text = stringResource(id = R.string.semester_short, i + 1),
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
                .fillMaxSize(),
            indicator = { state, trigger ->
                BsuProgressBarSwipeRefreshIndicator(state = state, trigger = trigger)
            },
            state = refreshState,
            onRefresh = updater::update,
        ) {
            HorizontalPager(
                count = subjects.size,
                state = state,
                modifier = Modifier
                    .fillMaxSize()


            ) { page ->

                if (visibleSubjects.getOrNull(page)?.isNotEmpty() == true) {
                    Page(
                        modifier = Modifier
                            .graphicsLayer {
                                translationY = refreshState.indicatorOffset
                            }
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .navigationBarsWithImePadding()
                            .padding(bottom = 10.dp),

                        subjects = visibleSubjects[page],
                        opened = openedSubjects,
                    )
                } else {
                    Box(Modifier
                        .fillMaxSize()) {

                        ErrorWidget(
                            title = stringResource(id = R.string.empty),
                            error = stringResource(id = R.string.subjects_not_found),
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 100.dp)
                        )
                    }
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
    opened : SnapshotStateList<Subject>,
    modifier: Modifier = Modifier
) {
    val mutableSubjects = remember {
        mutableStateListOf(*subjects.toTypedArray())
    }

    val subjectsInFirstColumn = remember {
        mutableStateOf(ceil(subjects.size/2f).roundToInt())
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

    val firstColumnItems = remember {
        mutableStateListOf<Subject>()
    }
    val secondColumnItems = remember {
        mutableStateListOf<Subject>()
    }

    LaunchedEffect(subjects) {
        if (mutableSubjects.toList() != subjects) {
            mutableSubjects.clear()
            mutableSubjects.addAll(subjects)
            subjectsInFirstColumn.value = mutableSubjects.size / 2
        }
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

    LaunchedEffect(key1 = subjectsInFirstColumn.value) {
        firstColumnItems.clear()
        firstColumnItems.addAll(mutableSubjects.subList(0, subjectsInFirstColumn.value))
        secondColumnItems.clear()
        secondColumnItems.addAll(
            mutableSubjects.subList(subjectsInFirstColumn.value, mutableSubjects.size))
    }

    Row(
        modifier = modifier
    ) {
        listOf(firstColumnItems,secondColumnItems).forEachIndexed { columnIndex, list ->
            Column(
                Modifier
                    .weight(1f)
                    .onSizeChanged {
                        columnHeight[columnIndex].value = it.height
                    }
            ) {
                list.forEachIndexed { idx, subj ->
                    key(subj.name) {

                        var visible by rememberSaveable {
                            mutableStateOf(false)
                        }
                        LaunchedEffect(Unit){
                            visible = true
                        }
                        AnimatedVisibility(
                            modifier = Modifier.padding(7.dp),
                            visible = visible,
                            enter = if (shrinkAnimationEnabled) slideInHorizontally {
                                if (columnIndex == 0) it else -it
                            }
                            else EnterTransition.None,
                            exit = ExitTransition.None

                        ) {

                            SubjectWidget(
                                subject = subj,
                                isOpened = subj in opened,
                                modifier = Modifier
                                    .applyIf(idx == list.lastIndex) {
                                        it.onSizeChanged {
                                            lastElemHeight[columnIndex].value = it.height
                                        }
                                    }
                            ) {
                                scope.launch(Dispatchers.Default) {
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

private suspend fun suspendRepeat(delay:Long = 100, max:Int = 3, shrink : () ->Boolean){
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

        subjects.size == 1 -> false

        (firstColumnHeight - secondColumnHeight).let {
            it > 0 && lastElemInFirstColumnHeight*1.1 < it &&
                    subjects.size > subjectsInFirstColumn.value - 1 &&
                    subjects.size >1 &&
                    subjectsInFirstColumn.value >0
        } -> {
            subjects.add(subjects.removeAt(subjectsInFirstColumn.value - 1))
            subjectsInFirstColumn.value--
            true
        }

        (secondColumnHeight - firstColumnHeight).let {
            it > 0 && lastElemInSecondColumnHeight*1.1  < it &&
                    subjects.size > subjectsInFirstColumn.value &&
                    subjects.size >1 &&
                    subjectsInFirstColumn.value >= 0
        } -> {
            subjects.add(
                subjectsInFirstColumn.value,
                subjects.removeLast()
            )
            subjectsInFirstColumn.value++
            true
        }
        else -> false
    }
}