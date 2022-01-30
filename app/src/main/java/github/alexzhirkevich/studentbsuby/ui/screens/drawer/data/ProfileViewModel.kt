package github.alexzhirkevich.studentbsuby.ui.screens.drawer.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.R
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.repo.LoginRepository
import github.alexzhirkevich.studentbsuby.repo.ProfileRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.logger.Logger
import github.alexzhirkevich.studentbsuby.util.setState
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val loginRepository: LoginRepository,
    private val logger: Logger,
) : ViewModel() {

    private val _photo = mutableStateOf<DataState<ImageBitmap>>(DataState.Empty)
    val photo: State<DataState<ImageBitmap>> = _photo

    private val _user = mutableStateOf<DataState<User>>(DataState.Empty)
    val user: State<DataState<User>> = _user

    init {
        updateUser()
        updatePhoto()
    }

    private fun updatePhoto() {
        profileRepository.photo()
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
            .launchIn(viewModelScope)
    }

    private fun updateUser() {
        profileRepository
            .user()
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
            }.launchIn(viewModelScope)
    }


    override fun onCleared() {
        super.onCleared()
        photo.value.valueOrNull()?.asAndroidBitmap()?.recycle()
    }

    fun logout(){
        loginRepository.logout()
    }
}