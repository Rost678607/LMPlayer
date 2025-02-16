package com.softwareforpeople.lmplayer.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class PermissionManager(private val activity: ComponentActivity) {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    init {
        initializePermissionLauncher()
        launch()
    }

    private fun initializePermissionLauncher() {
        requestPermissionLauncher = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Разрешение предоставлено
                println("Permission granted")
            } else {
                // Разрешение не предоставлено
                println("Permission denied")
            }
        }
    }

    private fun launch() {
        val sdkVersion: Int = Build.VERSION.SDK_INT
        readAudioPermission(sdkVersion)
    }

    private fun readAudioPermission(sdk: Int) {
        val permission = if (sdk >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        checkAndRequestPermission(permission)
    }

    private fun checkAndRequestPermission(permission: String) {
        when {
            ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Разрешение уже предоставлено
                println("Permission already granted")
            }

            else -> {
                // Запрашиваем разрешение
                requestPermissionLauncher.launch(permission)
            }
        }
    }
}