package com.vald3nir.toolkit.helpers.baseclasses

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

open class BaseActivity : ComponentActivity() {

    private var resultLauncher: ActivityResultLauncher<Intent>? = null
    private var resultLauncherListener: ((Intent?) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                resultLauncherListener?.invoke(result.data)
            }
        }
    }

    fun launchIntent(intent: Intent, listener: (Intent?) -> Unit) {
        resultLauncherListener = listener
        resultLauncher?.launch(intent)
    }
}