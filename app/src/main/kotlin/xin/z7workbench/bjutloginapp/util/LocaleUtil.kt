package xin.z7workbench.bjutloginapp.util

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.os.LocaleList
import org.jetbrains.anko.configuration
import org.jetbrains.anko.defaultSharedPreferences
import java.util.*

object LocaleUtil {
    private fun isAutoLanguageChanged(context: Context): Boolean {
        val appLocales = context.resources.configuration.locales
        val sysLocales = Resources.getSystem().configuration.locales
        return if (context.defaultSharedPreferences.getString("language", "0") != "0") {
            appLocales != sysLocales
        } else false
    }

    fun wrap(context: Context): ContextWrapper {
        val config = context.resources.configuration
        val type = context.defaultSharedPreferences.getString("language", null)
        if (type == null) {
            context.defaultSharedPreferences.edit().putString("language", "0").apply()
        }
        val locales = when (type) {
            "Auto" -> Resources.getSystem().configuration.locales
            "zh_CN" -> LocaleList(Locale.SIMPLIFIED_CHINESE)
            "en_US" -> LocaleList(Locale.US)
            else -> {
                context.defaultSharedPreferences.edit().putString("language", "Auto").apply()
                context.configuration.locales
            }
        }
        if (isAutoLanguageChanged(context) ||
                ((type != "Auto") && config.locales[0].language != type)) {
            config.setLocale(locales[0])
            config.setLocales(locales)
            config.setLayoutDirection(locales[0])
        }
        val newContext = context.createConfigurationContext(config)
        return ContextWrapper(newContext)
    }
}