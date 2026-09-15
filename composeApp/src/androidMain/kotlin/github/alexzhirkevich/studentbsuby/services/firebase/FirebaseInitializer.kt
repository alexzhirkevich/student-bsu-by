package github.alexzhirkevich.studentbsuby.services.firebase

import android.content.Context
import com.google.firebase.FirebaseApp

/**
 * Must be called from MainApplication.onCreate. Safe to call when
 * google-services.json is absent.
 */
fun initFirebase(context: Context) {
    kotlin.runCatching {
        FirebaseApp.initializeApp(context)
    }
}
