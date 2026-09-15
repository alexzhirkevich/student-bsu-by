package github.alexzhirkevich.studentbsuby.util

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * Holds the current activity for Play services flows that require one.
 * [set] must be called from MainActivity.onCreate and [clear] from onDestroy.
 */
object CurrentActivityHolder {

    private var ref = WeakReference<Activity>(null)

    val activity: Activity?
        get() = ref.get()

    fun set(activity: Activity) {
        ref = WeakReference(activity)
    }

    fun clear(activity: Activity) {
        if (ref.get() === activity) {
            ref = WeakReference(null)
        }
    }
}
