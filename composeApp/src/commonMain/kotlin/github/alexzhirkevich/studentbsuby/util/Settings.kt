package github.alexzhirkevich.studentbsuby.util

import com.russhwolf.settings.ObservableSettings

expect fun provideSettings(name: String): ObservableSettings

expect fun provideSecureSettings(name: String): ObservableSettings

expect fun provideDefaultSettings(): ObservableSettings
