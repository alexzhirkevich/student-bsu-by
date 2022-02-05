package github.alexzhirkevich.studentbsuby.ui.screens.drawer.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import github.alexzhirkevich.studentbsuby.R
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {


    fun onEmailClicked() {
        Intent(Intent.ACTION_SENDTO).apply {
            type = "text/html"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.app_email)))
            putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
        }.let {
            context.startActivity(it)
        }
    }

    fun onTgClicked() {

        Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.app_tg)))
            .apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }.let {
                context.startActivity(it)
            }
    }
}