package github.alexzhirkevich.studentbsuby.ui.screens.login

import androidx.compose.animation.*
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VpnKey
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import de.charlex.compose.HtmlText
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.navigation.navigate
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.common.DefaultTextInput
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.about.AboutViewModel
import github.alexzhirkevich.studentbsuby.ui.theme.LocalThemeSelector
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.exceptions.LoginException
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ButtonWidth = 200

@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    aboutViewModel: AboutViewModel = hiltViewModel()
) {

    val loggedIn = loginViewModel.loggedIn.value


    LaunchedEffect(key1 = loggedIn) {
        if (loggedIn.valueOrNull() == true) {
            navController.navigate(Route.DrawerScreen) {
                launchSingleTop = true
                popUpTo(Route.AuthScreen.route) {
                    inclusive = true
                    saveState = false
                }
            }
        }
    }


    LoginWidget(
        loginViewModel = loginViewModel,
        aboutViewModel =  aboutViewModel,
    )
    AnimatedVisibility(
        visible = loginViewModel.shouldShowSplashScreen.value,
        enter = EnterTransition.None,
        exit = fadeOut()
    ) {
        SplashScreen(loginViewModel.splashText.value)
    }
}

@Composable
fun SplashScreen(text : String = "") {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.primaryVariant)
    ){

        Box(
            Modifier
                .align(Alignment.Center)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.logo_size))

            )
        }

        var dots by remember {
            mutableStateOf("")
        }

        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(500)
                if (dots =="...")
                    dots=""
                else dots+='.'
            }
        }


        Text(
            text = text+dots,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .navigationBarsPadding()
        )
    }
}


@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
private fun LoginWidget(
    loginViewModel: LoginViewModel,
    aboutViewModel: AboutViewModel
) {

    val loginState by loginViewModel.loggedIn

    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(key1 = loginState) {
        (loginState as? DataState.Error)?.let {
            val message = (it.error as? LoginException)?.message
                ?: context.getString(it.message)
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        snackbarHost = { snack ->
            SnackbarHost(
                snack,
                modifier = Modifier
                    .navigationBarsWithImePadding()

            ) {
                Card(
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = it.message,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error
                    )
                }
//                Snackbar(
//                    snackbarData = it,
//                    backgroundColor = MaterialTheme.colors.secondary,
//                    contentColor = MaterialTheme.colors.error,
//                    actionColor = MaterialTheme.colors.error,
//
//                )
            }
        }
    ) {

        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
                .bsuBackgroundPattern(
                    MaterialTheme.colors.primary
                        .copy(alpha = .05f)
                )
        ) {

            Column(
                Modifier
                    .widthIn(max = 400.dp)
                    .align(Alignment.Center)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp)
                    .navigationBarsWithImePadding()

            ) {
                Spacer(
                    modifier = Modifier
                        .height(45.dp)
                )

                Box {
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(y = (-40).dp)
                            .size(80.dp)
                            .zIndex(2f),
                        elevation = 3.dp,
                        backgroundColor = MaterialTheme.colors.secondary,
                        shape = CircleShape
                    ) {
                        Box(
                            Modifier
                                .padding(5.dp)
                                .clip(CircleShape)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Login",
                                tint = MaterialTheme.colors.primary,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                    Card(
                        elevation = 3.dp,
                        backgroundColor = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .padding(horizontal = 35.dp)
                            .zIndex(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                elevation = 2.dp,
                                backgroundColor = MaterialTheme.colors.secondary,
                                shape = object : Shape {

                                    override fun createOutline(
                                        size: Size,
                                        layoutDirection: LayoutDirection,
                                        density: Density
                                    ): Outline {
                                        val cr = CornerRadius(
                                            (size.width-ButtonWidth*density.density*0.75f)/2,
                                            size.height/2)
                                        return Outline.Rounded(
                                            RoundRect(
                                                top = 0f, left = 0f,
                                                bottom = size.height, right = size.width,
                                                bottomLeftCornerRadius = cr,
                                                bottomRightCornerRadius = cr
                                            )
                                        )
                                    }
                                }
                            ) {

                                Box(
                                    Modifier
                                        .padding(
                                            top = 80.dp,
                                            bottom = 20.dp,
                                            start = 25.dp,
                                            end = 25.dp
                                        )
                                ) {
                                    LoginForm(
                                        loginViewModel = loginViewModel
                                    )
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
//                                    .background(MaterialTheme.colors.background)
                                    .padding(vertical = 10.dp)
                            ) {
                                CompositionLocalProvider(
                                    LocalTextStyle provides MaterialTheme.typography.caption
                                ) {

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Checkbox(
                                            checked = loginViewModel.autoLogin.value,
                                            enabled = loginViewModel.loggedIn.value !is DataState.Loading,
                                            onCheckedChange = loginViewModel::setAutoLogin,
                                            colors = CheckboxDefaults.colors(
                                                checkedColor = MaterialTheme.colors.primary,
                                                checkmarkColor = MaterialTheme.colors.onPrimary
                                            )
                                        )

                                        Text(text = stringResource(id = R.string.autologin))
                                    }

                                }

                                val keyboard = LocalSoftwareKeyboardController.current
                                Button(
                                    onClick = {
                                        loginViewModel.login()
                                        keyboard?.hide()

                                    },
                                    enabled = loginViewModel.loggedIn.value !is DataState.Loading,
                                    modifier = Modifier
                                        .width(ButtonWidth.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.btn_login),
                                        color = MaterialTheme.colors.onPrimary
                                    )
                                }

                                Spacer(modifier = Modifier.height(3.dp))


                                var showDialog by rememberSaveable {
                                    mutableStateOf(false)
                                }
                                if (showDialog) {
                                    Dialog(onDismissRequest = { showDialog = false }) {
                                        Card(
                                            backgroundColor = MaterialTheme.colors.secondary
                                        ) {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                modifier = Modifier.padding(
                                                    horizontal = 10.dp,
                                                    vertical = 5.dp
                                                )
                                            ) {
                                                Text(
                                                    text = stringResource(id = R.string.cant_login),
                                                    style = MaterialTheme.typography.subtitle1,
                                                    textAlign = TextAlign.Center,
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))
                                                HtmlText(
                                                    textId = R.string.cant_login_dialog,
                                                    style = MaterialTheme.typography.body1,
                                                    textAlign = TextAlign.Center,
                                                    urlSpanStyle = SpanStyle(
                                                        color = MaterialTheme.colors.primary,
                                                        textDecoration = TextDecoration.Underline
                                                    )
                                                )
                                                Spacer(modifier = Modifier.height(10.dp))

                                                Row(
                                                    Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.SpaceAround,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {

                                                    IconButton(onClick = aboutViewModel::onEmailClicked) {
                                                        Icon(
                                                            imageVector = Icons.Default.Email,
                                                            contentDescription = "E-mail",
                                                            tint = MaterialTheme.colors.primary,
                                                            modifier = Modifier.size(24.dp)
                                                        )
                                                    }

                                                    IconButton(onClick = aboutViewModel::onTgClicked) {
                                                        Image(
                                                            painter = painterResource(R.drawable.ic_telegram),
                                                            contentDescription = "Telegram",
                                                            modifier = Modifier.size(24.dp)
                                                        )
                                                    }

                                                    IconButton(onClick ={showDialog = false}) {
                                                        Icon(
                                                            imageVector = Icons.Default.Close,
                                                            tint = MaterialTheme.colors.onSecondary,
                                                            contentDescription = "Close",
                                                            modifier = Modifier.size(24.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                ClickableText(
                                    style = MaterialTheme.typography.caption,
                                    text = AnnotatedString(stringResource(R.string.cant_login))
                                ) {
                                    showDialog = true
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}


@ExperimentalCoroutinesApi
@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
private fun LoginForm(
    loginViewModel: LoginViewModel
){

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultTextInput(
            value = loginViewModel.loginText.value,
            onValueChange = loginViewModel::setLoginText,
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "Login")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            enabled = loginViewModel.loggedIn.value !is DataState.Loading,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.login),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(.5.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)

        )
        
        Spacer(modifier = Modifier.height(10.dp))


        var passwordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        DefaultTextInput(
            value = loginViewModel.passwordText.value,
            onValueChange = loginViewModel::setPasswordText,
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.VpnKey, contentDescription = "Password")
            },
            trailingIcon = {
                Icon(
                    imageVector = if (passwordVisible)
                        Icons.Default.VisibilityOff
                    else Icons.Default.Visibility,
                    contentDescription = "Show password",
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            passwordVisible = !passwordVisible
                        }
                )
            },
            enabled = loginViewModel.loggedIn.value !is DataState.Loading,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

            placeholder = {
                Text(
                    text = stringResource(id = R.string.password),
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(.5.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(10.dp))

        val capcha by loginViewModel.captchaBitmap.collectAsState()

        Box(Modifier
            .background(
                color = MaterialTheme.colors.background,
                shape = MaterialTheme.shapes.medium)
        ) {
            if (capcha is DataState.Loading) {
                BsuProgressBar(
                    Modifier.align(Alignment.Center),
                    tint = MaterialTheme.colors.primary,
                    size = 40.dp
                )
            }
            Row(
                modifier = Modifier
//                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .border(.5.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {

                val capchaModifier = remember {
                    Modifier
                        .height(44.dp)
                        .width((ButtonWidth - 44).dp)
//
                }

                when (capcha) {
                    is DataState.Success<*> -> Image(
                        bitmap = capcha.valueOrNull()!!,
                        contentDescription = "captcha",
                        contentScale = ContentScale.Crop,
                        modifier = capchaModifier,
                        colorFilter = if (!MaterialTheme.colors.isLight)
                            ColorFilter.colorMatrix(
                                colorMatrix = ColorMatrix(
                                    floatArrayOf(
                                        -1f, 0f, 0f, 0f, 255f,
                                        0f, -1f, 0f, 0f, 255f,
                                        0f, 0f, -1f, 0f, 255f,
                                        0f, 0f, 0f, 1f, 0f,
                                    )
                                )
                            ) else null
                    )
                    else -> Spacer(modifier = capchaModifier)
                }
                Box(
                    Modifier
                        .clickable(onClick = loginViewModel::updateCaptcha)
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Update captcha",
                        modifier = Modifier
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        val keyboardController = LocalSoftwareKeyboardController.current
        DefaultTextInput(
            value = loginViewModel.captchaText.collectAsState().value,
            onValueChange = loginViewModel::setCaptchaText,
            singleLine = true,
            enabled = loginViewModel.loggedIn.value !is DataState.Loading,
//            label = {
//                Text(
//                    text = stringResource(id = R.string.captcha),
//                )
//            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.captcha),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions {
                keyboardController?.hide()
            },
            maxLines = 1,
            modifier = Modifier
                .width(ButtonWidth.dp)
                .heightIn(min = 44.dp)
                .border(.5.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)

        )
    }
}