package github.alexzhirkevich.studentbsuby.ui.screens.drawer

import android.content.Context
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.repo.LoginRepository
import github.alexzhirkevich.studentbsuby.repo.Repository
import github.alexzhirkevich.studentbsuby.repo.ReviewRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.Updatable
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import github.alexzhirkevich.studentbsuby.workers.SynchronizationWorkerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import me.onebone.toolbar.ExperimentalToolbarApi
import javax.inject.Inject

@ExperimentalToolbarApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val photoRepository: Repository<Bitmap>,
    private val userRepository: Repository<User>,
    private val loginRepository: LoginRepository,
    private val reviewRepository: ReviewRepository,
    private val logger: Logger,
    private val synchronizationWorkerManager: SynchronizationWorkerManager
) : ViewModel(), Updatable {

    private val _photo = mutableStateOf<DataState<ImageBitmap>>(DataState.Empty)
    val photo: State<DataState<ImageBitmap>> = _photo

    private val _user = mutableStateOf<DataState<User>>(DataState.Empty)
    val user: State<DataState<User>> = _user

    private val _isUpdating = mutableStateOf(false)
    override val isUpdating: State<Boolean> get() = _isUpdating

    init {
        update()
    }

    override fun update() {
        updateUser()
        updatePhoto()
    }

    fun provideActivity(activity: ComponentActivity) {
        activity.lifecycleScope.launchWhenCreated {
            reviewRepository.tryShowReviewDialog(activity)
        }
    }

    private fun updatePhoto() {
        photoRepository.get()
            .flowOn(Dispatchers.IO)
            .onEach {
                _photo.value.valueOrNull()?.asAndroidBitmap()?.recycle()
                _photo.value = DataState.Success(it.asImageBitmap())
            }
            .onEmpty {
                if (_photo.value !is DataState.Success) {
                    _photo.value = DataState.Empty
                }
            }
            .catch {
                if (_photo.value !is DataState.Success){
                    _photo.value = DataState.Error(
                        R.string.error_load_photo,it
                    )
                }
                logger.log(
                    "Failed to get photo",
                    javaClass.simpleName,
                    Logger.LogLevel.Error,
                    it
                )
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    private fun updateUser() {
        userRepository.get()
            .flowOn(Dispatchers.IO)
            .onEach {
                _user.value = DataState.Success(it)
            }
            .onEmpty {
                if (_user.value !is DataState.Success) {
                    _user.value = DataState.Empty
                }
            }
            .catch {
                if (_user.value !is DataState.Success){
                    _user.value = DataState.Error(
                        R.string.error_load_user,it
                    )
                }
                logger.log(
                    "Failed to get user profile",
                    javaClass.simpleName,
                    Logger.LogLevel.Error,
                    it
                )
            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }


    override fun onCleared() {
        super.onCleared()
        photo.value.valueOrNull()?.asAndroidBitmap()?.recycle()
    }

    fun logout(){
        synchronizationWorkerManager.stop()
        loginRepository.logout()
    }
}