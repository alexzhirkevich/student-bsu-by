package github.alexzhirkevich.studentbsuby.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.util.sharedPreferences
import github.alexzhirkevich.studentbsuby.workers.SyncWorkerManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
class SettingsRepository @Inject constructor(
    preferences: SharedPreferences,
    @ApplicationContext context: Context,
    private val synchronizationWorkerManager: SyncWorkerManager,
) {
    var synchronizationEnabled by sharedPreferences(preferences,true){
//        with(synchronizationWorkerManager){
//            if (it) run() else stop()
//        }
    }

    var collectStatistics by sharedPreferences(preferences,true){
        FirebaseAnalytics.getInstance(context).setAnalyticsCollectionEnabled(it)
    }

    var collectCrashlytics by sharedPreferences(preferences,true){
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(it)
    }
}