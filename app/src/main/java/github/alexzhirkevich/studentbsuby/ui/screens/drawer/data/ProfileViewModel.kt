package github.alexzhirkevich.studentbsuby.ui.screens.drawer.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.repo.LoginRepository
import github.alexzhirkevich.studentbsuby.repo.ProfileRepository
import github.alexzhirkevich.studentbsuby.util.DataState
import github.alexzhirkevich.studentbsuby.util.setState
import github.alexzhirkevich.studentbsuby.util.valueOrNull
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _photo = mutableStateOf<DataState<ImageBitmap>>(DataState.Empty)
    val photo: State<DataState<ImageBitmap>> = _photo

    private val _user = mutableStateOf<DataState<User>>(DataState.Empty)
    val user : State<DataState<User>> = _user

    init {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                listOf(
                    async {
                        updateUser()
                    },
                    async {
                        updatePhoto()
                    }).awaitAll()
            }
        }
    }

    private suspend fun updatePhoto() {
        profileRepository.photo().collectLatest {
            setState {
                _photo.value.valueOrNull()?.asAndroidBitmap()?.recycle()
                _photo.value = DataState.Success(it.asImageBitmap())
            }
        }
    }

    private suspend fun updateUser(){
        profileRepository.user().collectLatest {
            setState {
                _user.value = DataState.Success(it)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        photo.value.valueOrNull()?.asAndroidBitmap()?.recycle()
    }

    fun logout(){
        loginRepository.logout()
    }
}