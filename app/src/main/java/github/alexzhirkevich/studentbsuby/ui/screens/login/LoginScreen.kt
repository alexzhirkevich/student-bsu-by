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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.navigation.Route
import github.alexzhirkevich.studentbsuby.navigation.navigate
import github.alexzhirkevich.studentbsuby.ui.common.BsuProgressBar
import github.alexzhirkevich.studentbsuby.ui.common.DefaultTextInput
import github.alexzhirkevich.studentbsuby.ui.theme.values.Colors
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.bsuBackgroundPattern
import github.alexzhirkevich.studentbsuby.util.exceptions.LoginException
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ButtonWidth = 200

@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
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
        loginViewModel = loginViewModel
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
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .navigationBarsPadding()
        )
    }
}

@ExperimentalComposeUiApi
@Composable
private fun LoginWidget(
    loginViewModel: LoginViewModel
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
                Snackbar(
                    snackbarData = it,
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.primary,
                    actionColor = MaterialTheme.colors.primary
                )
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
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 20.dp)
                    .navigationBarsWithImePadding()

            ) {
                Spacer(
                    modifier = Modifier
                        .height(150.dp)
                )

                Box {
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .absoluteOffset(y = (-40).dp)
                            .size(80.dp)
                            .zIndex(2f),
                        elevation = 3.dp,
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
                        backgroundColor = MaterialTheme.colors.background,
                        modifier = Modifier
                            .padding(horizontal = 35.dp)
                            .zIndex(1f)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Card(
                                elevation = 2.dp,
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
                                                checkedColor = MaterialTheme.colors.primary
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
                                        .clip(RoundedCornerShape(10.dp))
                                ) {
                                    Text(text = stringResource(id = R.string.btn_login))
                                }

                                Spacer(modifier = Modifier.height(3.dp))

                                ClickableText(
                                    style = MaterialTheme.typography.caption
                                        .copy(color = MaterialTheme.colors.primary),
                                    text = AnnotatedString(stringResource(R.string.cant_login))
                                ) {}
                            }
                        }
                    }
                }
            }
        }

    }
}

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

        Box(Modifier.background(MaterialTheme.colors.background)) {
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
                        modifier = capchaModifier
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