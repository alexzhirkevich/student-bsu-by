package github.alexzhirkevich.studentbsuby.repo

import android.content.SharedPreferences
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val preferences: SharedPreferences
) {

}