package github.alexzhirkevich.studentbsuby.di

import android.graphics.Bitmap
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import github.alexzhirkevich.studentbsuby.data.models.Subject
import github.alexzhirkevich.studentbsuby.data.models.User
import github.alexzhirkevich.studentbsuby.repo.*

@Module
@InstallIn(ViewModelComponent::class)
interface RepoModule {

    @Binds
    fun bindPhotoRepository(photoRepository: PhotoRepository) : Repository<Bitmap>

    @Binds
    fun bindUserRepository(userRepository: UserRepository) : Repository<User>

//    @Binds
//    fun bindSubjectsRepository(subjectsRepository: SubjectsRepository) : Repository<List<List<Subject>>>

    @Binds
    fun bindCurrentSemesterRepository(repository: CurrentSemesterRepository) : Repository<Int>
}