package com.lotarv.bigsteppa

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocaleUtils {
    fun setLocale(context: Context, langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
