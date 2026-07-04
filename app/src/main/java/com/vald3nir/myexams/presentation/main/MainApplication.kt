package com.vald3nir.myexams.presentation.main

import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.vald3nir.toolkit.core.baseclasses.BaseApplication
import com.vald3nir.toolkit.core.services.threads.ProfileVerifierLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : BaseApplication() {
    @Inject
    lateinit var profileVerifierLogger: ProfileVerifierLogger

    override fun onCreate() {
        super.onCreate()
        PDFBoxResourceLoader.init(this)
        setStrictModePolicy()
        profileVerifierLogger()
    }
}