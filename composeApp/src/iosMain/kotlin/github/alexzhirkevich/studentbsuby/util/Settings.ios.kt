package github.alexzhirkevich.studentbsuby.util

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import platform.Foundation.NSUserDefaults

actual fun provideSettings(name: String): ObservableSettings =
    NSUserDefaultsSettings(NSUserDefaults(suiteName = name))

actual fun provideSecureSettings(name: String): ObservableSettings =
    KeychainSettings(service = name)

actual fun provideDefaultSettings(): ObservableSettings =
    NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
