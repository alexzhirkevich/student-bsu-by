package github.alexzhirkevich.studentbsuby.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.repo.RemoteConfigRepository
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.about.AboutEventHandlerImpl
import github.alexzhirkevich.studentbsuby.ui.screens.drawer.about.IAboutEventHandler

@Module
@InstallIn(ViewModelComponent::class)
class AboutModule {

    @Provides
    fun provideEventHandler(
        @ApplicationContext context: Context,
        remoteConfigRepository: RemoteConfigRepository
    ) : IAboutEventHandler = AboutEventHandlerImpl(
        context = context, configRepository = remoteConfigRepository
    )
}