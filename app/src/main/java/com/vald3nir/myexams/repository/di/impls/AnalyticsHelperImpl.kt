package com.vald3nir.myexams.repository.di.impls

import android.util.Log
import com.vald3nir.myexams.BuildConfig
import com.vald3nir.toolkit.core.services.analytics.AnalyticsEvent
import com.vald3nir.toolkit.core.services.analytics.AnalyticsHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AnalyticsHelperImpl @Inject constructor() : AnalyticsHelper {

    override fun logEvent(event: AnalyticsEvent) {
        Log.d("AnalyticsHelper", "Received analytics event: $event")
    }

    override fun onLog(message: String) {
        if (BuildConfig.DEBUG) {
            println("My Exams -> $message")
        }
    }
}